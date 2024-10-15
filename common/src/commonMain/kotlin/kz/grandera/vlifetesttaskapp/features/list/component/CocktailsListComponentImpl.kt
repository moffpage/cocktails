package kz.grandera.vlifetesttaskapp.features.list.component

import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.material.ExperimentalMaterialApi

import org.koin.dsl.module
import org.koin.core.component.getScopeId
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore

import kz.grandera.vlifetesttaskapp.ui.list.CocktailsListContent
import kz.grandera.vlifetesttaskapp.core.componentcontext.AppComponentContext
import kz.grandera.vlifetesttaskapp.core.extensions.states
import kz.grandera.vlifetesttaskapp.core.scope.koinScope
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.State
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.Intent
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.Cocktail
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.Model
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.CocktailModel

internal class CocktailsListComponentImpl(
    componentContext: AppComponentContext,
    private val onShowCocktail: (cocktailId: Long) -> Unit
) : CocktailsListComponent,
    AppComponentContext by componentContext
{
    private val koinScope = koinScope(
        cocktailsListModule,
        scopeId = getScopeId(),
        qualifier = qualifier<CocktailsListComponent>()
    )

    private val store = instanceKeeper.getStore {
        koinScope.inject<CocktailsListStore>().value
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content(modifier: Modifier) {
        CocktailsListContent(modifier = modifier, component = this)
    }

    override val model: Value<Model> = store.states.map { state -> state.toModel() }

    override fun reload() {
        store.accept(intent = Intent.Shuffle)
    }

    override fun showCocktail(cocktail: CocktailModel) {
        onShowCocktail(cocktail.id)
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
