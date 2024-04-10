package kz.grandera.vlifetesttaskapp.ui_components.theming

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit

@Composable
public fun Typography(): Typography = Typography(
    h1 = TextStyle(
        fontSize = 30.0.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = aliceFontFamily(),
        lineHeight = 34.0.sp,
        letterSpacing = (-0.24).sp
    ),
    h2 = TextStyle(
        fontSize = 24.0.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = aliceFontFamily(),
        lineHeight = 20.0.sp,
        letterSpacing = (-0.24).sp
    ),
    h3 = TextStyle(
        fontSize = 20.0.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = aliceFontFamily(),
        lineHeight = 24.0.sp,
        letterSpacing = (-0.24).sp
    ),
    h4 = TextStyle(
        fontSize = 16.0.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = aliceFontFamily(),
        lineHeight = 18.0.sp,
        letterSpacing = TextUnit.Unspecified
    ),
    body1 = TextStyle(
        fontSize = 13.0.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = aliceFontFamily(),
        lineHeight = 16.0.sp,
        letterSpacing = TextUnit.Unspecified
    )
)