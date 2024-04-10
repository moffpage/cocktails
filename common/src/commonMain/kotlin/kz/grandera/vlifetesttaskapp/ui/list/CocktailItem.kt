package kz.grandera.vlifetesttaskapp.ui.list

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import coil3.compose.SubcomposeAsyncImage

import dev.icerock.moko.resources.compose.painterResource

import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.CocktailModel
import kz.grandera.vlifetesttaskapp.ui_components.theming.AppTheme
import kz.grandera.vlifetesttaskapp.ui_components.resources.UiComponentImages

@Composable
internal fun CocktailItem(
    modifier: Modifier = Modifier,
    cocktail: CocktailModel,
    onClick: (CocktailModel) -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.large)
            .clickable { onClick(cocktail) },
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.matchParentSize(),
            model = cocktail.imageUrl,
            error = {
                Image(
                    painter = painterResource(
                        imageResource = UiComponentImages.cocktailPlaceholder(
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
            modifier = Modifier.align(alignment = Alignment.Center),
            text = cocktail.name,
            style = MaterialTheme.typography.h4
                .copy(
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center
                )
        )
    }
}