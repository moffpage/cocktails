package kz.grandera.vlifetesttaskapp.ui_components.theming

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme

public val lightColorScheme: ColorScheme = lightColorScheme(
    primary = Color(color = 0xFFECBE43),
    onPrimary = Color(color = 0xFFFFFFFF),

    background = Color(color = 0xFFFFFFFF),
    onBackground = Color(color = 0xFF212121),

    surface = Color(color = 0xFFF5F5F5),
    onSurface = Color(color = 0xFF9E9E9E),

    secondary = Color(color = 0xFF4469EB),

    error = Color(color = 0xFFB3261E)
)

public val darkColorScheme: ColorScheme = lightColorScheme
    .copy(
        surface = Color(color = 0xFF212121),

        background = Color(color = 0xFF151515),
        onBackground = Color(color = 0xFFFFFFFF),
    )