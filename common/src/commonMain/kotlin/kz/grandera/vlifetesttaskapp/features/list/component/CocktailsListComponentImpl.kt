package kz.grandera.vlifetesttaskapp.features.list.component

import kotlinx.coroutines.delay

import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.material.ExperimentalMaterialApi

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.decompose.value.MutableValue

import kz.grandera.vlifetesttaskapp.ui.list.CocktailsListContent
import kz.grandera.vlifetesttaskapp.api.cocktails.CocktailsApi
import kz.grandera.vlifetesttaskapp.api.entity.CocktailEntity
import kz.grandera.vlifetesttaskapp.core.coroutines.LaunchStrategy
import kz.grandera.vlifetesttaskapp.core.coroutines.extensions.safeLaunch
import kz.grandera.vlifetesttaskapp.core.coroutines.extensions.invokeOnFailure
import kz.grandera.vlifetesttaskapp.core.componentcontext.AppComponentContext
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.Model
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.CocktailModel

internal class CocktailsListComponentImpl(
    componentContext: AppComponentContext,
    private val cocktailsApi: CocktailsApi,
    private val onShowCocktail: (cocktailId: Long) -> Unit
) : CocktailsListComponent,
    AppComponentContext by componentContext
{
    private val _model: MutableValue<FilteredModel> =
        MutableValue(FilteredModel.initialState())

    init {
        filterCocktails(isAlcoholic = false)
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content(modifier: Modifier) {
        CocktailsListContent(modifier = modifier, component = this)
    }

    override val model: Value<Model> = _model

    override fun reload() {
        safeLaunch(LaunchStrategy.keepPrevious("reload")) {
            _model.update {
                it.copy(
                    isRefreshing = true
                )
            }

            delay(1000L)

            _model.update {
                it.copy(
                    cocktails = it.filteredCocktails.shuffled(),
                    isRefreshing = false
                )
            }
        }
    }

    override fun showCocktail(cocktail: CocktailModel) {
        onShowCocktail(cocktail.id)
    }

    override fun clearSearch() {
        findCocktail("")
    }

    override fun findCocktail(searchQuery: String) {
        _model.update {
            it.copy(
                cocktails = it.filteredCocktails.filter { cocktail ->
                    cocktail.name.startsWith(
                        prefix = searchQuery,
                        ignoreCase = true
                    )
                },
                searchQuery = searchQuery
            )
        }
    }

    override fun refetchCocktails() {
        if (_model.value.listsAlcoholicCocktails) {
            displayAlcoholicCocktails()
        } else {
            displayNonAlcoholicCocktails()
        }
    }

    override fun displayAlcoholicCocktails() {
        filterCocktails(isAlcoholic = true)
    }

    override fun displayNonAlcoholicCocktails() {
        filterCocktails(isAlcoholic = false)
    }

    private fun filterCocktails(isAlcoholic: Boolean) {
        val savedIfAnyCocktails = _model.value.filteredCocktails
        val predicate = savedIfAnyCocktails.any { cocktail ->
            cocktail.isAlcoholic == isAlcoholic
        }
        if (predicate) {
            _model.update {
                it.copy(
                    cocktails = savedIfAnyCocktails.filter { cocktail ->
                        cocktail.isAlcoholic == isAlcoholic
                    },
                    listsAlcoholicCocktails = isAlcoholic
                )
            }
        } else {
            _model.update {
                it.copy(
                    isError = false,
                    isLoading = true
                )
            }

            safeLaunch(LaunchStrategy.killPrevious("filter")) {
                val cocktails = cocktailsApi.getCocktails(isAlcoholic = isAlcoholic)
                    .cocktails
                    .map {
                        it.toCocktail(
                            isAlcoholic = isAlcoholic
                        )
                    }

                _model.update {
                    it.copy(
                        isLoading = false,
                        cocktails = cocktails,
                        filteredCocktails = savedIfAnyCocktails + cocktails,
                        listsAlcoholicCocktails = isAlcoholic
                    )
                }
            }.invokeOnFailure {
                _model.update {
                    it.copy(
                        isError = true,
                        isLoading = false
                    )
                }
            }
        }
    }
}

internal fun CocktailEntity.toCocktail(isAlcoholic: Boolean): CocktailModel =
    CocktailModel(
        id = this.id.toLong(),
        name = this.name,
        imageUrl = this.imageUrl,
        isAlcoholic = isAlcoholic
    )

private data class FilteredModel(
    override val isError: Boolean,
    override val isLoading: Boolean,
    override val isRefreshing: Boolean,
    override val cocktails: List<CocktailModel>,
    val filteredCocktails: List<CocktailModel>,
    override val searchQuery: String,
    override val listsAlcoholicCocktails: Boolean
) : Model(
    isError = isError,
    isLoading = isLoading,
    isRefreshing = isRefreshing,
    cocktails = cocktails,
    searchQuery = searchQuery,
    listsAlcoholicCocktails = listsAlcoholicCocktails
) {
    companion object {
        fun initialState(): FilteredModel = FilteredModel(
            isError = false,
            isLoading = false,
            isRefreshing = false,
            searchQuery = "",
            cocktails = emptyList(),
            filteredCocktails = emptyList(),
            listsAlcoholicCocktails = false
        )
    }
}