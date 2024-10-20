package kz.grandera.vlifetesttaskapp.ui.list

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBarsPadding

import org.jetbrains.compose.resources.stringResource

import com.arkivanov.decompose.extensions.compose.subscribeAsState

import kz.grandera.vlifetesttaskapp.common.Res
import kz.grandera.vlifetesttaskapp.common.alcoholic
import kz.grandera.vlifetesttaskapp.common.cocktails
import kz.grandera.vlifetesttaskapp.common.non_alcoholic

import kz.grandera.vlifetesttaskapp.ui.animation.CombinedSharedTransitionScope
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.ui_components.error.ErrorContent
import kz.grandera.vlifetesttaskapp.ui_components.loading.LoadingContent
import kz.grandera.vlifetesttaskapp.ui_components.segment.SegmentedControl
import kz.grandera.vlifetesttaskapp.ui_components.textfield.SearchBar

@Composable
@ExperimentalMaterialApi
@ExperimentalSharedTransitionApi
internal fun CocktailsListContent(
    component: CocktailsListComponent,
    modifier: Modifier = Modifier,
    sharedTransitionScope: CombinedSharedTransitionScope? = null
) {
    val model by component.model.subscribeAsState()

    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .background(color = MaterialTheme.colors.background)
            .clickable(
                onClick = { focusManager.clearFocus() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            )
            .fillMaxSize()
    ) {
        if (model.isError) {
            ErrorContent(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(horizontal = 24.dp),
                onRetry = { component.refetchCocktails() }
            )
        } else {
            Box {
                val pullRefreshState = rememberPullRefreshState(
                    refreshing = model.isRefreshing,
                    onRefresh = { component.reload() }
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .pullRefresh(
                            state = pullRefreshState,
                            enabled = !model.isLoading
                        ),
                    columns = GridCells.Fixed(count = 2),
                    contentPadding = PaddingValues(
                        start = 8.dp,
                        end = 8.dp,
                        top = 12.dp,
                        bottom = WindowInsets.navigationBars
                            .getBottom(density = LocalDensity.current)
                            .dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(space = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
                ) {
                    item(
                        key = "title",
                        span = { GridItemSpan(currentLineSpan = maxLineSpan) }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(bottom = 20.dp)
                                .statusBarsPadding(),
                            text = stringResource(resource = Res.string.cocktails),
                            style = MaterialTheme.typography.h1
                                .copy(color = MaterialTheme.colors.onBackground)
                        )
                    }

                    item(
                        key = "search_bar",
                        span = { GridItemSpan(currentLineSpan = maxLineSpan) }
                    ) {
                        SearchBar(
                            modifier = Modifier.height(height = 36.dp),
                            text = model.searchQuery,
                            onValueChange = { text ->
                                component.findCocktail(
                                    searchQuery = text
                                )
                            },
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus() }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                capitalization = KeyboardCapitalization.Words
                            ),
                            onTrailingContentClicked = { component.clearSearch() }
                        )
                    }

                    item(
                        key = "filters_header",
                        span = { GridItemSpan(currentLineSpan = maxLineSpan) }
                    ) {
                        ProvideTextStyle(
                            value = MaterialTheme.typography.h4
                                .copy(textAlign = TextAlign.Center)
                        ) {
                            SegmentedControl(
                                modifier = Modifier.padding(vertical = 8.dp),
                                selected = { index ->
                                    if (index == 0) {
                                        !model.listsAlcoholicCocktails
                                    } else {
                                        model.listsAlcoholicCocktails
                                    }
                                },
                                titles = listOf(
                                    stringResource(resource = Res.string.non_alcoholic),
                                    stringResource(resource = Res.string.alcoholic)
                                ),
                                onSegmentClick = { index ->
                                    if (index == 0) {
                                        component.displayNonAlcoholicCocktails()
                                    } else {
                                        component.displayAlcoholicCocktails()
                                    }
                                }
                            )
                        }
                    }

                    items(
                        items = model.cocktails,
                        key = { cocktail -> cocktail.id }
                    ) { cocktail ->
                        CocktailItem(
                            modifier = Modifier.aspectRatio(ratio = 1f),
                            cocktail = cocktail,
                            onClick = { selectedCocktail ->
                                component.showCocktail(
                                    cocktail = selectedCocktail
                                )
                            },
                            sharedTransitionScope = sharedTransitionScope
                        )
                    }
                }

                PullRefreshIndicator(
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .statusBarsPadding(),
                    state = pullRefreshState,
                    refreshing = model.isRefreshing,
                )
            }
        }

        if (model.isLoading) {
            LoadingContent()
        }
    }
}