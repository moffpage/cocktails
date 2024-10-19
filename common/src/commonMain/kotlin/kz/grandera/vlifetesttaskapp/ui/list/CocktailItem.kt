package kz.grandera.vlifetesttaskapp.ui.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.SubcomposeAsyncImage
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.CocktailModel
import kz.grandera.vlifetesttaskapp.ui_components.resources.cocktailPlaceholderResource
import kz.grandera.vlifetesttaskapp.ui_components.theming.AppTheme
import org.jetbrains.compose.resources.painterResource

@ExperimentalSharedTransitionApi
@Composable
internal fun SharedTransitionScope.CocktailItem(
    cocktail: CocktailModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: (CocktailModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clickable { onClick(cocktail) },
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState("item-image-${cocktail.id}"),
                    animatedVisibilityScope = animatedVisibilityScope,
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
                .sharedBounds(
                    sharedContentState = rememberSharedContentState("item-text-${cocktail.id}"),
                    animatedVisibilityScope = animatedVisibilityScope,
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
