package kz.grandera.vlifetesttaskapp.ui.theming.text.font

import androidx.compose.ui.text.font.Font as ComposeFont

import kz.grandera.vlifetesttaskapp.theming.text.font.Font

internal fun Font.toComposeFont(): ComposeFont =
    ComposeFont(
        resId = resource.fontResourceId,
        weight = weight.toComposeFontWeight()
    )