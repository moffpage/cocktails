package kz.grandera.vlifetesttaskapp.theming.colors

public val lightColorScheme: ColorScheme = ColorScheme(
    primary = Color(hexValue = 0xFFECBE43),
    onPrimary = Color(hexValue = 0xFFFFFFFF),

    background = Color(hexValue = 0xFFFFFFFF),
    onBackground = Color(hexValue = 0xFF212121),

    surface = Color(hexValue = 0xFFF5F5F5),
    onSurface = Color(hexValue = 0xFF9E9E9E),

    secondary = Color(hexValue = 0xFF4469EB),

    error = Color(hexValue = 0xFFB3261E)
)

public val darkColorScheme: ColorScheme = lightColorScheme
    .copy(
        surface = Color(hexValue = 0xFF212121),

        background = Color(hexValue = 0xFF151515),
        onBackground = Color(hexValue = 0xFFFFFFFF),
    )