package kz.grandera.vlifetesttaskapp.features.list.component

import com.arkivanov.decompose.value.Value

import kz.grandera.vlifetesttaskapp.component.Component
import kz.grandera.vlifetesttaskapp.core.event.EventsProducer
import kz.grandera.vlifetesttaskapp.core.componentcontext.AppComponentContext

public interface CocktailsListComponent : Component, EventsProducer<CocktailsListComponent.Event> {
    public fun interface Factory {
        public fun create(componentContext: AppComponentContext): CocktailsListComponent
    }

    public data class Model(
        public val isError: Boolean,
        public val isLoading: Boolean,
        public val isRefreshing: Boolean,
        public val cocktails: List<CocktailModel>,
        public val searchQuery: String,
        public val listsAlcoholicCocktails: Boolean,
    )

    public data class CocktailModel(
        public val id: Long,
        public val name: String,
        public val imageUrl: String,
    )

    public sealed interface Event {
        public data class ShowCocktail(
            public val cocktailId: Long
        ) : Event
    }

    public val model: Value<Model>

    public fun reload()

    public fun clearSearch()

    public fun showCocktail(cocktail: CocktailModel)

    public fun findCocktail(searchQuery: String)

    public fun refetchCocktails()

    public fun displayAlcoholicCocktails()

    public fun displayNonAlcoholicCocktails()
}