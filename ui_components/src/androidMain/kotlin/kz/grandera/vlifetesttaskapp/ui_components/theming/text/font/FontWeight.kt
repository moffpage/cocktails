package kz.grandera.vlifetesttaskapp.ui_components.theming.text.font

import androidx.compose.ui.text.font.FontWeight as ComposeFontWeight

internal fun FontWeight.toComposeFontWeight(): ComposeFontWeight =
    when (this) {
        FontWeight.Normal -> ComposeFontWeight.Normal
        FontWeight.Medium -> ComposeFontWeight.Medium
        FontWeight.SemiBold -> ComposeFontWeight.SemiBold
        FontWeight.Bold -> ComposeFontWeight.Bold
        FontWeight.ExtraBold -> ComposeFontWeight.ExtraBold
    }