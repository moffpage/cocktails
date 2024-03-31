package kz.grandera.vlifetesttaskapp.ui_components.theming.text.font

import dev.icerock.moko.resources.FontResource

public data class CommonFont(
    public val weight: FontWeight,
    public val resource: FontResource,
)

public fun CommonFont.toFontFamily(): FontFamily = FontFamily(fonts = listOf(this))
