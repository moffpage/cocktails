package kz.grandera.vlifetesttaskapp.ui_components.theming.text

import androidx.compose.material.Typography

public val ComposeTypography: Typography = with(TypographyFactory) {
    Typography(
        h1 = h1.toComposeTextStyle(),
        h2 = h2.toComposeTextStyle(),
        h3 = h3.toComposeTextStyle(),
        h4 = h4.toComposeTextStyle(),
        body1 = body1.toComposeTextStyle()
    )
}