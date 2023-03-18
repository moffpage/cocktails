package kz.grandera.vlifetesttaskapp.theming.text.font

import kz.grandera.vlifetesttaskapp.SharedRes

public val AliceFontFamily: FontFamily =
    Font(
        weight = FontWeight.Normal,
        resource = SharedRes.fonts.Alice.regular,
    ).toFontFamily()