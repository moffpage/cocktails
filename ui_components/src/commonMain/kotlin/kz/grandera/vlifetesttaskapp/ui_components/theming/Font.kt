package kz.grandera.vlifetesttaskapp.ui_components.theming

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.runtime.Composable

import org.jetbrains.compose.resources.Font

import kz.grandera.vlifetesttaskapp.ui_components.Res
import kz.grandera.vlifetesttaskapp.ui_components.alice_regular

@Composable
internal fun aliceFontFamily(): FontFamily = FontFamily(Font(Res.font.alice_regular))