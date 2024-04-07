package kz.grandera.vlifetesttaskapp.ui.list.preview

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.decompose.value.MutableValue

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.Model

private val cocktails = listOf(
    CocktailsListComponent.CocktailModel(
        id = 1,
        name = "Martini",
        imageUrl = ""
    ),
    CocktailsListComponent.CocktailModel(
        id = 2,
        name = "Mojito",
        imageUrl = ""
    ),
    CocktailsListComponent.CocktailModel(
        id = 3,
        name = "Blue Margarita",
        imageUrl = ""
    ),
    CocktailsListComponent.CocktailModel(
        id = 4,
        name = "Boulevardier",
        imageUrl = ""
    ),
    CocktailsListComponent.CocktailModel(
        id = 5,
        name = "Mai-Tai",
        imageUrl = ""
    ),
    CocktailsListComponent.CocktailModel(
        id = 6,
        name = "Cosmopolitan",
        imageUrl = ""
    ),
    CocktailsListComponent.CocktailModel(
        id = 7,
        name = "The Joe Steel",
        imageUrl = ""
    ),
    CocktailsListComponent.CocktailModel(
        id = 8,
        name = "Aunt Nancy",
        imageUrl = ""
    ),
)

internal class CocktailsListComponentPreviewContentProvider : PreviewParameterProvider<CocktailsListComponent> {
    private val _model = MutableValue(
        initialValue = Model(
            isError = false,
            isLoading = false,
            isRefreshing = false,
            cocktails = cocktails,
            searchQuery = "",
            listsAlcoholicCocktails = false
        )
    )

    override val values: Sequence<CocktailsListComponent> = sequenceOf(
        object : CocktailsListComponent {
            override val model: Value<Model> = _model
            override fun reload() {
                _model.update { model ->
                    model.copy(
                        isRefreshing = true
                    )
                }
                _model.update { model ->
                    model.copy(
                        cocktails = model.cocktails.shuffled()
                    )
                }
                _model.update { model ->
                    model.copy(
                        isRefreshing = false
                    )
                }
            }
            override fun clearSearch() {
                _model.update { model ->
                    model.copy(
                        cocktails = cocktails,
                        searchQuery = ""
                    )
                }
            }
            override fun showCocktail(cocktailId: Long) { }
            override fun findCocktail(searchQuery: String) {
                _model.update { model ->
                    model.copy(
                        cocktails = cocktails.filter { cocktail ->
                            cocktail.name.contains(
                                other = searchQuery,
                                ignoreCase = false
                            )
                        },
                        searchQuery = searchQuery,
                    )
                }
            }
            override fun refetchCocktails() { }
            override fun displayAlcoholicCocktails() {
                _model.update { model ->
                    model.copy(
                        listsAlcoholicCocktails = true
                    )
                }
            }
            override fun displayNonAlcoholicCocktails() {
                _model.update { model ->
                    model.copy(
                        listsAlcoholicCocktails = false
                    )
                }
            }
        }
    )
}