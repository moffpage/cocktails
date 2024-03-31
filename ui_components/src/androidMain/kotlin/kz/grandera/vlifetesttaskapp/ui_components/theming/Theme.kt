package kz.grandera.vlifetesttaskapp.ui_components.theming

import androidx.compose.runtime.Composable
import androidx.compose.material.MaterialTheme

import kz.grandera.vlifetesttaskapp.ui_components.theming.text.ComposeTypography
import kz.grandera.vlifetesttaskapp.ui_components.theming.shapes.ComposeShapes
import kz.grandera.vlifetesttaskapp.ui_components.theming.colors.darkColors
import kz.grandera.vlifetesttaskapp.ui_components.theming.colors.lightColors

@Composable
public fun VlifeTestTaskAppTheme(
    applyDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (applyDarkTheme) darkColors else lightColors,
        shapes = ComposeShapes,
        typography = ComposeTypography,
        content = content
    )
}
