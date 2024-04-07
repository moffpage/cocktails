package kz.grandera.vlifetesttaskapp.ui.details.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter

import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent
import kz.grandera.vlifetesttaskapp.ui.details.CocktailDetailsContent
import kz.grandera.vlifetesttaskapp.ui_components.theming.VlifeTestTaskAppTheme

@Preview
@Composable
private fun CocktailDetailsContentLightPreview(
    @PreviewParameter(CocktailDetailsPreviewContentProvider::class)
    component: CocktailDetailsComponent
) {
    VlifeTestTaskAppTheme(applyDarkTheme = false) {
        CocktailDetailsContent(component = component)
    }
}

@Preview
@Composable
private fun CocktailDetailsContentDarkPreview(
    @PreviewParameter(CocktailDetailsPreviewContentProvider::class)
    component: CocktailDetailsComponent
) {
    VlifeTestTaskAppTheme(applyDarkTheme = true) {
        CocktailDetailsContent(component = component)
    }
}