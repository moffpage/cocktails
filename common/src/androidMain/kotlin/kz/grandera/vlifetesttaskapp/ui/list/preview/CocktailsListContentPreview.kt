package kz.grandera.vlifetesttaskapp.ui.list.preview

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.runtime.Composable
import androidx.compose.material.ExperimentalMaterialApi

import kz.grandera.vlifetesttaskapp.ui.list.CocktailsListContent
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.ui_components.theming.VlifeTestTaskAppTheme

@Preview
@Composable
private fun CocktailsListContentLightPreview(
    @PreviewParameter(CocktailsListComponentPreviewContentProvider::class)
    component: CocktailsListComponent
) {
    VlifeTestTaskAppTheme(applyDarkTheme = false) {
        @OptIn(ExperimentalMaterialApi::class)
        CocktailsListContent(component = component)
    }
}

@Preview
@Composable
private fun CocktailsListContentDarkPreview(
    @PreviewParameter(CocktailsListComponentPreviewContentProvider::class)
    component: CocktailsListComponent
) {
    VlifeTestTaskAppTheme(applyDarkTheme = true) {
        @OptIn(ExperimentalMaterialApi::class)
        CocktailsListContent(component = component)
    }
}