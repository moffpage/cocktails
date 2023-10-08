package kz.grandera.vlifetesttaskapp.features.cocktails.details.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable

import kz.grandera.vlifetesttaskapp.features.cocktails.details.store.CocktailDetailsStore.State
import kz.grandera.vlifetesttaskapp.features.cocktails.details.store.CocktailDetailsStore.Intent

internal interface CocktailDetailsStore : Store<Intent, State, Nothing> {
    data class State(
        val cocktailId: Long,
        val isError: Boolean,
        val category: String,
        val imageUrl: String,
        val glassType: String,
        val isAlcoholic: Boolean,
        val cocktailName: String,
        val preparationInstruction: String,
    ) : JvmSerializable

    sealed interface Intent : JvmSerializable {
        data object Refresh : Intent
    }
}