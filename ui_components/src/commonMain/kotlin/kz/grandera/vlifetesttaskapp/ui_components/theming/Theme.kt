package kz.grandera.vlifetesttaskapp.ui_components.theming

import androidx.compose.runtime.Composable
import androidx.compose.material.Surface
import androidx.compose.material.MaterialTheme

@Composable
public fun VlifeTestTaskAppTheme(
    appTheme: AppTheme,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (appTheme == AppTheme.Light) lightColors else darkColors,
        shapes = Shapes,
        typography = Typography(),
        content = { Surface(content = content) }
    )
}
