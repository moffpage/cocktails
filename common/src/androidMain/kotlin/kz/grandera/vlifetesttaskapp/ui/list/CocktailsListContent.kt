package kz.grandera.vlifetesttaskapp.ui.list

import android.annotation.SuppressLint

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.Surface
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.gestures.detectTapGestures

import coil.compose.AsyncImage

import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

import kz.grandera.vlifetesttaskapp.R
import kz.grandera.vlifetesttaskapp.ui.error.ErrorContent
import kz.grandera.vlifetesttaskapp.ui.common.TextField
import kz.grandera.vlifetesttaskapp.ui.loading.LoadingContent
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.CocktailModel
import kz.grandera.vlifetesttaskapp.resources.Strings

@OptIn(ExperimentalMaterialApi::class)
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
internal fun CocktailsListContent(component: CocktailsListComponent) {
    val model by component.model.subscribeAsState()

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() },
                    onDoubleTap = { component.viewTimeTravel() }
                )
            }
            .fillMaxSize()
    ) {
        if (model.isError) {
            ErrorContent(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .align(alignment = Alignment.Center),
                onRetry = { component.refetchCocktails() }
            )
        } else {
            Box {
                val navigationBarPadding = WindowInsets.navigationBars
                    .getBottom(density = LocalDensity.current)

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
                        bottom = navigationBarPadding.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(space = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
                ) {
                    item(
                        key = "title",
                        span = { GridItemSpan(currentLineSpan = maxLineSpan)}
                    ) {
                        Text(
                            modifier = Modifier
                                .statusBarsPadding()
                                .padding(bottom = 20.dp),
                            text = stringResource(id = Strings.cocktails.resourceId),
                            style = MaterialTheme.typography.h1
                                .copy(color = MaterialTheme.colors.onBackground)
                        )
                    }

                    item(
                        key = "search_bar",
                        span = { GridItemSpan(currentLineSpan = maxLineSpan) }
                    ) {
                        TextField(
                            modifier = Modifier.height(height = 36.dp),
                            value = model.searchQuery,
                            onValueChange = { text ->
                                component.findCocktail(
                                    searchQuery = text
                                )
                            },
                            textStyle = MaterialTheme.typography.h4,
                            leadingContent = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            },
                            trailingContent = {
                                if (model.searchQuery.isNotEmpty()) {
                                    Icon(
                                        modifier = Modifier
                                            .clickable { component.clearSearch() },
                                        painter = painterResource(id = R.drawable.close),
                                        contentDescription = null
                                    )
                                }
                            },
                            placeholder = stringResource(
                                id = Strings.search.resourceId
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus() }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                capitalization = KeyboardCapitalization.Words
                            )
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
                            TwoSegmentsControl(
                                modifier = Modifier.padding(vertical = 8.dp),
                                selected = !model.listsAlcoholicCocktails,
                                firstSegmentTitle = stringResource(
                                    id = Strings.nonAlcoholic.resourceId
                                ),
                                secondSegmentTitle = stringResource(
                                    id = Strings.alcoholic.resourceId
                                ),
                                onFirstSegmentClick = { component.displayNonAlcoholicCocktails() },
                                onSecondSegmentClick = { component.displayAlcoholicCocktails() },
                            )
                        }
                    }

                    items(
                        items = model.cocktails,
                        key = { cocktail -> cocktail.id }
                    ) { cocktail ->
                        CocktailItem(
                            modifier = Modifier
                                .aspectRatio(ratio = 1f),
                            cocktail = cocktail,
                            onClick = { cocktailId ->
                                component.showCocktail(
                                    cocktailId = cocktailId
                                )
                            }
                        )
                    }
                }

                PullRefreshIndicator(
                    modifier = Modifier.align(alignment = Alignment.TopCenter),
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

@Composable
private fun TwoSegmentsControl(
    modifier: Modifier = Modifier,
    selected: Boolean,
    firstSegmentTitle: String,
    secondSegmentTitle: String,
    onFirstSegmentClick: () -> Unit,
    onSecondSegmentClick: () -> Unit
) {
    val tab: @Composable RowScope.(
        text: String,
        selected: Boolean,
        onClick: () -> Unit,
    ) -> Unit = {text, isSelected, onClick ->
        val tabBackgroundColor = if (isSelected) {
            MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.surface
        }
        Surface(
            modifier = Modifier
                .weight(weight = 1f)
                .clip(shape = CircleShape)
                .clickable(onClick = onClick),
            color = tabBackgroundColor,
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                text = text,
            )
        }
    }

    Surface(
        modifier = modifier,
        shape = CircleShape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 2.dp)
                .clip(shape = CircleShape),
        ) {
            tab(
                text = firstSegmentTitle,
                selected = selected,
                onClick = onFirstSegmentClick
            )
            tab(
                text = secondSegmentTitle,
                selected = !selected,
                onClick = onSecondSegmentClick
            )
        }
    }
}

@Composable
private fun CocktailItem(
    modifier: Modifier = Modifier,
    cocktail: CocktailModel,
    onClick: (Long) -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.large)
            .clickable { onClick(cocktail.id) },
    ) {
        AsyncImage(
            modifier = Modifier
                .matchParentSize(),
            model = cocktail.imageUrl,
            contentDescription = cocktail.name
        )
        Text(
            modifier = Modifier
                .align(alignment = Alignment.Center),
            text = cocktail.name,
            style = MaterialTheme.typography.h4
                .copy(
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center
                )
        )
    }
}