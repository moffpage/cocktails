package kz.grandera.vlifetesttaskapp.ui.theming.colors

import androidx.compose.ui.graphics.Color as ComposeColor

import kz.grandera.vlifetesttaskapp.theming.colors.Color

internal fun Color.toComposeColor(): ComposeColor =
    ComposeColor(color = hexValue)