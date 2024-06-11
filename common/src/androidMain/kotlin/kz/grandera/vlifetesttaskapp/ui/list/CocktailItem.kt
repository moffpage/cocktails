package kz.grandera.vlifetesttaskapp.ui.list

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio

import org.jetbrains.compose.ui.tooling.preview.Preview

import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent.CocktailModel
import kz.grandera.vlifetesttaskapp.ui_components.theming.ThemedAsyncImage
import kz.grandera.vlifetesttaskapp.ui_components.resources.UiComponentImages
import kz.grandera.vlifetesttaskapp.ui_components.theming.VlifeTestTaskAppTheme

@Composable
internal fun CocktailItem(
    modifier: Modifier = Modifier,
    cocktail: CocktailModel,
    onClick: (Long) -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.large)
            .clickable { onClick(cocktail.id) },
    ) {
        ThemedAsyncImage(
            modifier = Modifier.matchParentSize(),
            model = cocktail.imageUrl,
            lightPlaceholderPainter = painterResource(
                id = UiComponentImages.cocktailPlaceholderLight.drawableResId
            ),
            darkPlaceholderPainter = painterResource(
                id = UiComponentImages.cocktailPlaceholderDark.drawableResId
            ),
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

@Preview
@Composable
private fun CocktailItemLightPreview() {
    VlifeTestTaskAppTheme(applyDarkTheme = false) {
        CocktailItem(
            modifier = Modifier.aspectRatio(ratio = 1f),
            cocktail = CocktailModel(
                id = 1,
                name = "Martini",
                imageUrl = ""
            ),
            onClick = { }
        )
    }
}

@Preview
@Composable
private fun CocktailItemDarkPreview() {
    VlifeTestTaskAppTheme(applyDarkTheme = true) {
        CocktailItem(
            modifier = Modifier.aspectRatio(ratio = 1f),
            cocktail = CocktailModel(
                id = 1,
                name = "Martini",
                imageUrl = ""
            ),
            onClick = { }
        )
    }
}