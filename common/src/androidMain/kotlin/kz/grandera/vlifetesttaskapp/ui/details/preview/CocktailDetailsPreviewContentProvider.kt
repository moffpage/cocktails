package kz.grandera.vlifetesttaskapp.ui.details.preview

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.MutableValue

import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent

internal class CocktailDetailsPreviewContentProvider : PreviewParameterProvider<CocktailDetailsComponent> {
    override val values: Sequence<CocktailDetailsComponent> = sequenceOf(
        object : CocktailDetailsComponent {
            override val model: Value<CocktailDetailsComponent.Model> = MutableValue(
                initialValue = CocktailDetailsComponent.Model(
                    isError = false,
                    category = CocktailDetailsComponent.DrinkCategory.Cocktail,
                    imageUrl = "",
                    glassType = "Polycarbonate Glass",
                    isAlcoholic = false,
                    cocktailName = "Martini",
                    preparationInstruction = "Instructions for preparing Martini"
                )
            )

            override fun navigateBack() { }
            override fun refetchDetails() { }
        }
    )
}