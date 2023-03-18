package kz.grandera.vlifetesttaskapp.ui.theming.text.font

import androidx.compose.ui.text.font.FontWeight as ComposeFontWeight

import kz.grandera.vlifetesttaskapp.theming.text.font.FontWeight

internal fun FontWeight.toComposeFontWeight(): ComposeFontWeight =
    when (this) {
        FontWeight.Normal -> ComposeFontWeight.Normal
        FontWeight.Medium -> ComposeFontWeight.Medium
        FontWeight.SemiBold -> ComposeFontWeight.SemiBold
        FontWeight.Bold -> ComposeFontWeight.Bold
        FontWeight.ExtraBold -> ComposeFontWeight.ExtraBold
    }