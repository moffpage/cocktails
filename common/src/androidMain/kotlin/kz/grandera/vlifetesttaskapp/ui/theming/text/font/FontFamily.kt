package kz.grandera.vlifetesttaskapp.ui.theming.text.font

import androidx.compose.ui.text.font.FontFamily as ComposeFontFamily

import kz.grandera.vlifetesttaskapp.theming.text.font.FontFamily

internal fun FontFamily.toComposeFontFamily(): ComposeFontFamily =
    ComposeFontFamily(fonts = fonts.map { font -> font.toComposeFont() })