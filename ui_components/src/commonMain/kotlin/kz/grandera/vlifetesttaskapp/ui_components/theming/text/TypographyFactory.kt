package kz.grandera.vlifetesttaskapp.ui_components.theming.text

import kz.grandera.vlifetesttaskapp.ui_components.theming.text.font.FontWeight
import kz.grandera.vlifetesttaskapp.ui_components.theming.text.font.AliceFontFamily

public val TypographyFactory: Typography = Typography(
    h1 = TextStyle(
        fontSize = 30.0,
        fontWeight = FontWeight.Normal,
        fontFamily = AliceFontFamily,
        lineHeight = 34.0,
        letterSpacing = -0.24
    ),
    h2 = TextStyle(
        fontSize = 24.0,
        fontWeight = FontWeight.Normal,
        fontFamily = AliceFontFamily,
        lineHeight = 20.0,
        letterSpacing = -0.24
    ),
    h3 = TextStyle(
        fontSize = 20.0,
        fontWeight = FontWeight.Normal,
        fontFamily = AliceFontFamily,
        lineHeight = 24.0,
        letterSpacing = -0.24
    ),
    h4 = TextStyle(
        fontSize = 16.0,
        fontWeight = FontWeight.Normal,
        fontFamily = AliceFontFamily,
        lineHeight = 18.0,
        letterSpacing = null
    ),
    body1 = TextStyle(
        fontSize = 13.0,
        fontWeight = FontWeight.Normal,
        fontFamily = AliceFontFamily,
        lineHeight = 16.0,
        letterSpacing = null
    )
)