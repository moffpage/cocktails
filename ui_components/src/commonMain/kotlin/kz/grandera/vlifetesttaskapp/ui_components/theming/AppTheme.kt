package kz.grandera.vlifetesttaskapp.ui_components.theming

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.ProvidableCompositionLocal

public enum class AppTheme {
    Light, Dark;
    public operator fun not(): AppTheme = if (this == Light) Dark else Light
}

public fun AppTheme(isLight: Boolean): AppTheme = if (isLight) AppTheme.Light else AppTheme.Dark

public val LocalAppTheme: ProvidableCompositionLocal<MutableState<AppTheme>> = compositionLocalOf {
    mutableStateOf(value = AppTheme.Light)
}