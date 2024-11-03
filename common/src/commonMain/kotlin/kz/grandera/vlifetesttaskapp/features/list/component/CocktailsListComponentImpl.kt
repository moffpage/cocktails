package kz.grandera.vlifetesttaskapp.features.list.component

import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.serialization.Serializable

import org.koin.dsl.module
import org.koin.core.component.getScopeId
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.mvikotlin.core.instancekeeper.getStore

import kz.grandera.vlifetesttaskapp.core.scope.koinScope
import kz.grandera.vlifetesttaskapp.core.lifecycle.coroutineScope
import kz.grandera.vlifetesttaskapp.core.extensions.states
import kz.grandera.vlifetesttaskapp.core.extensions.childSlotDismissEvents
import kz.grandera.vlifetesttaskapp.core.componentcontext.AppComponentContext
import kz.grandera.vlifetesttaskapp.core.componentcontext.wrapComponentContext
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.State
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.Intent
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.Cocktail
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.Model
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.CocktailModel
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponentImpl

internal class CocktailsListComponentImpl(
    componentContext: AppComponentContext
) : CocktailsListComponent,
    AppComponentContext by componentContext
{
    private val scope = coroutineScope()
    private val koinScope = koinScope(
        cocktailsListModule,
        scopeId = getScopeId(),
        qualifier = qualifier<CocktailsListComponent>()
    )

    private val storeFactory by koinScope.inject<CocktailsListStore>()
    private val childComponentFactory by koinScope.inject<CocktailDetailsComponent.Factory>()

    private val store = instanceKeeper.getStore { storeFactory }

    private val sheetNavigation = SlotNavigation<CocktailDetails>()
    private val detailsChildSlot = childSlot(
        source = sheetNavigation,
        serializer = CocktailDetails.serializer(),
        childFactory = { configuration, context ->
            childComponentFactory.create(
                cocktailId = configuration.cocktailId,
                componentContext = wrapComponentContext(
                    context = context,
                    parentScopeId = koinScope.id
                )
            )
        }
    )

    override val model: Value<Model> = store.states
        .map { state -> state.toModel() }

    override val detailsModalBottomSheetChild: Value<ChildSlot<*, CocktailDetailsComponent>> =
        detailsChildSlot

    init {
        detailsChildSlot.childSlotDismissEvents()
            .onEach { dismissDetails() }
            .launchIn(scope = scope)
    }

    override fun reload() {
        store.accept(intent = Intent.Shuffle)
    }

    override fun dismissDetails() {
        sheetNavigation.dismiss()
    }

    override fun showCocktail(cocktail: CocktailModel) {
        sheetNavigation.activate(
            CocktailDetails(
                cocktailId = cocktail.id
            )
        )
    }

    override fun clearSearch() {
        store.accept(
            intent = Intent.Search(
                query = ""
            )
        )
    }

    override fun findCocktail(searchQuery: String) {
        store.accept(
            intent = Intent.Search(
                query = searchQuery
            )
        )
    }

    override fun refetchCocktails() {
        if (model.value.listsAlcoholicCocktails) {
            displayAlcoholicCocktails()
        } else {
            displayNonAlcoholicCocktails()
        }
    }

    override fun displayAlcoholicCocktails() {
        store.accept(
            intent = Intent.Filter(
                isAlcoholic = true
            )
        )
    }

    override fun displayNonAlcoholicCocktails() {
        store.accept(
            intent = Intent.Filter(
                isAlcoholic = false
            )
        )
    }
}

@Serializable
private data class CocktailDetails(val cocktailId: Long)

private val cocktailsListModule = module {
    scope<CocktailsListComponent> {
        scoped<CocktailsListStore> {
            CocktailsListStore(
                storeFactory = get(),
                mainContext = get(qualifier = named("Main")),
                ioContext = get(qualifier = named("IO")),
                cocktailsApi = get()
            )
        }

        scoped<CocktailDetailsComponent.Factory> {
            CocktailDetailsComponent.Factory { cocktailId, componentContext ->
                CocktailDetailsComponentImpl(
                    cocktailId = cocktailId,
                    componentContext = componentContext
                )
            }
        }
    }
}

private fun State.toModel(): Model = Model(
    isError = this.isError,
    isLoading = this.isLoading,
    isRefreshing = this.isRefreshing,
    searchQuery = this.searchQuery,
    cocktails = this.filteredCocktails.map { cocktail -> cocktail.toCocktailModel() },
    listsAlcoholicCocktails = this.filteredCocktails.any { cocktail -> cocktail.isAlcoholic }
)

private fun Cocktail.toCocktailModel(): CocktailModel = CocktailModel(
    id = this.id,
    name = this.name,
    imageUrl = "${this.imageUrl}/preview",
)
