package kz.grandera.vlifetesttaskapp.theming.text.font

import dev.icerock.moko.resources.FontResource

public data class Font(
    public val weight: FontWeight,
    public val resource: FontResource,
)

public fun Font.toFontFamily(): FontFamily = FontFamily(fonts = listOf(this))
