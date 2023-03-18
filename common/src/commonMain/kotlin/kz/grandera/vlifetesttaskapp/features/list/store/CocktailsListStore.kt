package kz.grandera.vlifetesttaskapp.features.list.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable

import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.State
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.Intent

internal interface CocktailsListStore : Store<Intent, State, Nothing> {
    data class Cocktail(
        val id: Long,
        val name: String,
        val imageUrl: String,
        val isAlcoholic: Boolean,
    ) : JvmSerializable

    data class State(
        val isError: Boolean,
        val isLoading: Boolean,
        val isRefreshing: Boolean,
        val searchQuery: String,
        val cocktails: List<Cocktail>,
        val filteredCocktails: List<Cocktail>
    ) : JvmSerializable

    sealed interface Intent : JvmSerializable {
        object Shuffle : Intent
        data class Search(val query: String) : Intent
        data class Filter(val isAlcoholic: Boolean) : Intent
    }
}