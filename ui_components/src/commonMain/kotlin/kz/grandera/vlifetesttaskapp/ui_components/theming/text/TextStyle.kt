package kz.grandera.vlifetesttaskapp.ui_components.theming.text

import kz.grandera.vlifetesttaskapp.ui_components.theming.text.font.FontFamily
import kz.grandera.vlifetesttaskapp.ui_components.theming.text.font.FontWeight

public data class TextStyle(
    public val fontSize: Double,
    public val fontWeight: FontWeight,
    public val fontFamily: FontFamily,
    public val lineHeight: Double,
    public val letterSpacing: Double?
)
