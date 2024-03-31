package kz.grandera.vlifetesttaskapp.ui_components.theming.text.font

import androidx.compose.ui.text.font.FontFamily as ComposeFontFamily

internal fun FontFamily.toComposeFontFamily(): ComposeFontFamily =
    ComposeFontFamily(fonts = fonts.map { font -> font.toComposeFont() })