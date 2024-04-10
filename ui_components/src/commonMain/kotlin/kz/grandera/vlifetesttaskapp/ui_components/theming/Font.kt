package kz.grandera.vlifetesttaskapp.ui_components.theming

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.runtime.Composable

import dev.icerock.moko.resources.compose.fontFamilyResource

import kz.grandera.vlifetesttaskapp.ui_components.UiComponentsRes

@Composable
internal fun aliceFontFamily(): FontFamily = fontFamilyResource(
    fontResource = UiComponentsRes.fonts.alice_regular
)