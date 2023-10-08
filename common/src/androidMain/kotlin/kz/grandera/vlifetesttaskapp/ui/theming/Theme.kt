package kz.grandera.vlifetesttaskapp.ui.theming

import androidx.compose.runtime.Composable
import androidx.compose.material.MaterialTheme
import kz.grandera.vlifetesttaskapp.ui.theming.text.Typography
import kz.grandera.vlifetesttaskapp.ui.theming.shapes.Shapes

import kz.grandera.vlifetesttaskapp.ui.theming.colors.darkColors
import kz.grandera.vlifetesttaskapp.ui.theming.colors.lightColors

@Composable
public fun VlifeTestTaskAppTheme(
    applyDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (applyDarkTheme) darkColors else lightColors,
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}
