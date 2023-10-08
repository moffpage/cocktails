package kz.grandera.vlifetesttaskapp.features.list.component

import com.arkivanov.decompose.value.Value

public interface CocktailsListComponent {
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

    public val model: Value<Model>

    public fun reload()

    public fun clearSearch()

    public fun showCocktail(cocktailId: Long)

    public fun findCocktail(searchQuery: String)

    public fun refetchCocktails()

    public fun displayAlcoholicCocktails()

    public fun displayNonAlcoholicCocktails()
}