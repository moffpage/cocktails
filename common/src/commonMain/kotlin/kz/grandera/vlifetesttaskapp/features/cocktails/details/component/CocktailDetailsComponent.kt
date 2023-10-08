package kz.grandera.vlifetesttaskapp.features.cocktails.details.component

import com.arkivanov.decompose.value.Value

public interface CocktailDetailsComponent {
    public data class Model(
        public val isError: Boolean,
        public val category: DrinkCategory?,
        public val imageUrl: String,
        public val glassType: String,
        public val isAlcoholic: Boolean,
        public val cocktailName: String,
        public val preparationInstruction: String,
    )

    public enum class DrinkCategory {
        Beer,
        Soft,
        Shot,
        Cocoa,
        Shake,
        Punch,
        Other,
        Coffee,
        Liqueur,
        Cocktail,
        Ordinary,
    }

    public val model: Value<Model>

    public fun navigateBack()

    public fun refetchDetails()
}