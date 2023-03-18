package kz.grandera.vlifetesttaskapp.ui.theming.text

import androidx.compose.material.Typography

import kz.grandera.vlifetesttaskapp.theming.text.TypographyFactory

public val Typography: Typography = with(TypographyFactory) {
    Typography(
        h1 = h1.toComposeTextStyle(),
        h2 = h2.toComposeTextStyle(),
        h3 = h3.toComposeTextStyle(),
        h4 = h4.toComposeTextStyle(),
        body1 = body1.toComposeTextStyle()
    )
}