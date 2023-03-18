package kz.grandera.vlifetesttaskapp.ui.theming.colors

import androidx.compose.material.Colors
import androidx.compose.material.lightColors

import kz.grandera.vlifetesttaskapp.theming.colors.darkColorScheme
import kz.grandera.vlifetesttaskapp.theming.colors.lightColorScheme

public val lightColors: Colors = with(lightColorScheme) {
    lightColors(
        primary = primary.toComposeColor(),
        onPrimary = onPrimary.toComposeColor(),

        background = background.toComposeColor(),
        onBackground = onBackground.toComposeColor(),

        surface = surface.toComposeColor(),
        onSurface = surface.toComposeColor(),

        secondary = secondary.toComposeColor(),

        error = error.toComposeColor()
    )
}

public val darkColors: Colors = with(darkColorScheme) {
    lightColors
        .copy(
            surface = surface.toComposeColor(),

            background = background.toComposeColor(),
            onBackground = onBackground.toComposeColor(),
        )
}