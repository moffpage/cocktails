package kz.grandera.vlifetesttaskapp.ui.list

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import org.jetbrains.compose.resources.painterResource

import coil3.compose.SubcomposeAsyncImage

import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.CocktailModel
import kz.grandera.vlifetesttaskapp.ui.animation.SharedTransitionKeys
import kz.grandera.vlifetesttaskapp.ui.animation.CombinedSharedTransitionScope
import kz.grandera.vlifetesttaskapp.ui_components.theming.AppTheme
import kz.grandera.vlifetesttaskapp.ui_components.resources.cocktailPlaceholderResource

@Composable
@ExperimentalSharedTransitionApi
internal fun CocktailItem(
    cocktail: CocktailModel,
    onClick: (CocktailModel) -> Unit,
    modifier: Modifier = Modifier,
    sharedTransitionScope: CombinedSharedTransitionScope? = null
) {
    Box(modifier = modifier.clickable { onClick(cocktail) }) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .then(
                    sharedTransitionScope?.run {
                        Modifier.sharedBounds(
                            key = SharedTransitionKeys.itemImage(cocktail.id)
                        )
                    } ?: Modifier
                )
                .clip(shape = MaterialTheme.shapes.large)
                .matchParentSize(),
            model = cocktail.imageUrl,
            error = {
                Image(
                    painter = painterResource(
                        resource = cocktailPlaceholderResource(
                            theme = AppTheme(
                                isLight = MaterialTheme.colors.isLight
                            )
                        )
                    ),
                    contentDescription = cocktail.name
                )
            },
            success = { state ->
                Image(
                    painter = state.painter,
                    contentDescription = cocktail.name
                )
            },
            contentDescription = cocktail.name
        )
        Text(
            modifier = Modifier
                .then(
                    sharedTransitionScope?.run {
                        Modifier.sharedBounds(
                            key = SharedTransitionKeys.itemText(cocktail.id)
                        )
                    } ?: Modifier
                )
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