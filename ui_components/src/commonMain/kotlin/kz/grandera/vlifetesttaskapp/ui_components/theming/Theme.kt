package kz.grandera.vlifetesttaskapp.ui_components.theming

import androidx.compose.ui.graphics.luminance
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme

@Composable
public fun VlifeTestTaskAppTheme(
    appTheme: AppTheme,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (appTheme == AppTheme.Light) lightColorScheme else darkColorScheme,
        shapes = Shapes,
        typography = Typography(),
        content = { Surface(content = content) }
    )
}

@get:Composable
public val ColorScheme.isLight: Boolean get() = this.background.luminance() > 0.5