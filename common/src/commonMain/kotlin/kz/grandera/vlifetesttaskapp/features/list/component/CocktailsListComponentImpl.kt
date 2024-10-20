package kz.grandera.vlifetesttaskapp.features.list.component

import kotlin.coroutines.CoroutineContext

import org.koin.core.component.inject
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.instancekeeper.getStore

import kz.grandera.vlifetesttaskapp.api.cocktails.CocktailsApi
import kz.grandera.vlifetesttaskapp.core.extensions.states
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.State
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.Intent
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.Cocktail
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.Model
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.CocktailModel

internal class CocktailsListComponentImpl(
    componentContext: ComponentContext,
    private val onShowCocktail: (cocktail: CocktailModel) -> Unit
) : CocktailsListComponent,
    KoinComponent,
    ComponentContext by componentContext
{
    private val mainContext by inject<CoroutineContext>(qualifier = named(name = "Main"))
    private val ioContext by inject<CoroutineContext>(qualifier = named(name = "IO"))
    private val cocktailsApi by inject<CocktailsApi>()
    private val storeFactory by inject<StoreFactory>()

    private val store = instanceKeeper.getStore {
        CocktailsListStore(
            storeFactory = storeFactory,
            mainContext = mainContext,
            ioContext = ioContext,
            cocktailsApi = cocktailsApi
        )
    }

    override val model: Value<Model> = store.states
        .map { state -> state.toModel() }

    override fun reload() {
        store.accept(intent = Intent.Shuffle)
    }

    override fun showCocktail(cocktail: CocktailModel) {
        onShowCocktail(cocktail)
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
