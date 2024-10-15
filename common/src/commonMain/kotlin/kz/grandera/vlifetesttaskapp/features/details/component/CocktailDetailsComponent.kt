package kz.grandera.vlifetesttaskapp.features.details.component

import com.arkivanov.decompose.value.Value

import kz.grandera.vlifetesttaskapp.core.componentcontext.AppComponentContext
import kz.grandera.vlifetesttaskapp.component.Component

public interface CocktailDetailsComponent : Component {
    public fun interface Factory {
        public fun create(
            onBack: () -> Unit,
            cocktailId: Long,
            componentContext: AppComponentContext
        ): CocktailDetailsComponent
    }

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