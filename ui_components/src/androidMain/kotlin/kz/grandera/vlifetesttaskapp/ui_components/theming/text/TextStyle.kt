package kz.grandera.vlifetesttaskapp.ui_components.theming.text

import androidx.compose.ui.text.TextStyle as ComposeTextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit

import kz.grandera.vlifetesttaskapp.ui_components.theming.text.font.toComposeFontFamily
import kz.grandera.vlifetesttaskapp.ui_components.theming.text.font.toComposeFontWeight

internal fun TextStyle.toComposeTextStyle(): ComposeTextStyle =
    ComposeTextStyle(
        fontSize = fontSize.sp,
        fontWeight = fontWeight.toComposeFontWeight(),
        fontFamily = fontFamily.toComposeFontFamily(),
        lineHeight = lineHeight.sp,
        letterSpacing = letterSpacing?.sp ?: TextUnit.Unspecified
    )