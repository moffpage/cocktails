package kz.grandera.vlifetesttaskapp.ui_components.resources

import dev.icerock.moko.resources.ImageResource

import kz.grandera.vlifetesttaskapp.ui_components.UiComponentsRes
import kz.grandera.vlifetesttaskapp.ui_components.theming.AppTheme

public object UiComponentImages {
    public fun cocktailPlaceholder(theme: AppTheme): ImageResource =
        if (theme == AppTheme.Light) {
            UiComponentsRes.images.cocktail_placeholder_light
        }
        else {
            UiComponentsRes.images.cocktail_placeholder_dark
        }
}