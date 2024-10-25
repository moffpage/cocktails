package kz.grandera.vlifetesttaskapp.features.details.component

import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.decompose.value.MutableValue

import kz.grandera.vlifetesttaskapp.ui.details.CocktailDetailsContent
import kz.grandera.vlifetesttaskapp.api.cocktails.CocktailsApi
import kz.grandera.vlifetesttaskapp.core.componentcontext.AppComponentContext
import kz.grandera.vlifetesttaskapp.core.coroutines.LaunchStrategy
import kz.grandera.vlifetesttaskapp.core.coroutines.extensions.safeLaunch
import kz.grandera.vlifetesttaskapp.core.coroutines.extensions.invokeOnFailure
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent.Model
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent.DrinkCategory

internal class CocktailDetailsComponentImpl(
    componentContext: AppComponentContext,
    private val cocktailId: Long,
    private val cocktailsApi: CocktailsApi,
    private val onNavigateBack: () -> Unit,
) : CocktailDetailsComponent,
    AppComponentContext by componentContext
{
    private val _model: MutableValue<Model> =
        MutableValue(
            Model(
                isError = false,
                category = null,
                imageUrl = "",
                glassType = "",
                isAlcoholic = false,
                cocktailName = "",
                preparationInstruction = ""
            )
        )

    init {
        fetchDetails()
    }

    @Composable
    override fun Content(modifier: Modifier) {
        CocktailDetailsContent(modifier = modifier, component = this)
    }

    override val model: Value<Model> = _model

    override fun navigateBack() {
        onNavigateBack()
    }

    override fun fetchDetails() {
        _model.update { it.copy(isError = false) }

        safeLaunch(LaunchStrategy.killPrevious("load")) {
            cocktailsApi.getCocktailDetails(cocktailId = cocktailId).cocktails
                .firstOrNull()
                ?.let { cocktailDetails ->
                    _model.update {
                        it.copy(
                            category = DrinkCategory.entries.firstOrNull { category ->
                                category.name in cocktailDetails.category.orEmpty()
                            },
                            imageUrl = cocktailDetails.imageUrl,
                            glassType = cocktailDetails.glassType.orEmpty(),
                            isAlcoholic = cocktailDetails.alcoholIndication == "Alcoholic",
                            cocktailName = cocktailDetails.name,
                            preparationInstruction = cocktailDetails.preparationInstruction.orEmpty()
                        )
                    }
                }
        }.invokeOnFailure {
            _model.update {
                it.copy(isError = true)
            }
        }
    }
}