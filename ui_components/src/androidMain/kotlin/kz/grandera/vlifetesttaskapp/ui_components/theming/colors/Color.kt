package kz.grandera.vlifetesttaskapp.ui_components.theming.colors

import androidx.compose.ui.graphics.Color as ComposeColor

internal fun Color.toComposeColor(): ComposeColor =
    ComposeColor(color = hexValue)