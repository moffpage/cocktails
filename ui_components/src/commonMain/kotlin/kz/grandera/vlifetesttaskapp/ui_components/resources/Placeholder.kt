package kz.grandera.vlifetesttaskapp.ui_components.resources

import org.jetbrains.compose.resources.DrawableResource

import kz.grandera.vlifetesttaskapp.ui_components.Res
import kz.grandera.vlifetesttaskapp.ui_components.cocktail_placeholder_dark
import kz.grandera.vlifetesttaskapp.ui_components.cocktail_placeholder_light
import kz.grandera.vlifetesttaskapp.ui_components.theming.AppTheme

public fun cocktailPlaceholderResource(theme: AppTheme): DrawableResource =
    if (theme == AppTheme.Light) {
        Res.drawable.cocktail_placeholder_light
    } else {
        Res.drawable.cocktail_placeholder_dark
    }