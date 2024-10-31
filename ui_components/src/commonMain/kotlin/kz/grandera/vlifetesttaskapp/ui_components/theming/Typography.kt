package kz.grandera.vlifetesttaskapp.ui_components.theming

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.runtime.Composable
import androidx.compose.material3.Typography

@Composable
public fun Typography(): Typography = Typography(
    headlineLarge = TextStyle(
        fontSize = 30.0.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = aliceFontFamily(),
        lineHeight = 34.0.sp,
        letterSpacing = (-0.24).sp
    ),
    headlineMedium = TextStyle(
        fontSize = 24.0.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = aliceFontFamily(),
        lineHeight = 20.0.sp,
        letterSpacing = (-0.24).sp
    ),
    headlineSmall = TextStyle(
        fontSize = 16.0.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = aliceFontFamily(),
        lineHeight = 18.0.sp,
        letterSpacing = TextUnit.Unspecified
    ),
    bodySmall = TextStyle(
        fontSize = 13.0.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = aliceFontFamily(),
        lineHeight = 16.0.sp,
        letterSpacing = TextUnit.Unspecified
    )
)