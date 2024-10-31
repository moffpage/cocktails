package kz.grandera.vlifetesttaskapp.ios

import platform.UIKit.UIViewController

import androidx.compose.ui.window.ComposeUIViewController
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.isSystemInDarkTheme

import kz.grandera.vlifetesttaskapp.ui.list.CocktailsListContent
import kz.grandera.vlifetesttaskapp.ui_components.theming.AppTheme
import kz.grandera.vlifetesttaskapp.ui_components.theming.LocalAppTheme
import kz.grandera.vlifetesttaskapp.ui_components.theming.VlifeTestTaskAppTheme
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent

public fun CocktailsViewController(
    component: CocktailsListComponent,
    shakeDetector: ShakeDetector
): UIViewController = ComposeUIViewController {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val appThemeState = remember(isSystemInDarkTheme) {
        mutableStateOf(
            value = if (isSystemInDarkTheme) {
                AppTheme.Dark
            } else {
                AppTheme.Light
            }
        )
    }
    CompositionLocalProvider(LocalAppTheme provides appThemeState) {
        VlifeTestTaskAppTheme(appTheme = appThemeState.value) {
            @OptIn(ExperimentalMaterial3Api::class)
            CocktailsListContent(component)

            LaunchedEffect(Unit) {
                shakeDetector.shakeEvents.collect {
                    appThemeState.value = !appThemeState.value
                }
            }
        }
    }
}