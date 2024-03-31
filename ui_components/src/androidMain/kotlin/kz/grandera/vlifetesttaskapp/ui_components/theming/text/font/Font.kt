package kz.grandera.vlifetesttaskapp.ui_components.theming.text.font

import androidx.compose.ui.text.font.Font as ComposeFont

internal fun CommonFont.toComposeFont(): ComposeFont =
    ComposeFont(
        resId = resource.fontResourceId,
        weight = weight.toComposeFontWeight()
    )