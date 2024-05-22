package kz.grandera.vlifetesttaskapp.ios

import platform.UIKit.UIViewController

import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize

import com.arkivanov.essenty.backhandler.BackDispatcher

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay

import kz.grandera.vlifetesttaskapp.ui.root.CocktailsContent
import kz.grandera.vlifetesttaskapp.ui_components.theming.AppTheme
import kz.grandera.vlifetesttaskapp.ui_components.theming.LocalAppTheme
import kz.grandera.vlifetesttaskapp.ui_components.theming.VlifeTestTaskAppTheme
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent

public fun CocktailsViewController(
    component: CocktailsComponent,
    shakeDetector: ShakeDetector,
    backDispatcher: BackDispatcher
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
            @OptIn(
                ExperimentalMaterialApi::class,
                ExperimentalDecomposeApi::class
            )
            PredictiveBackGestureOverlay(
                modifier = Modifier.fillMaxSize(),
                backIcon = null,
                backDispatcher = backDispatcher
            ) {
                CocktailsContent(component = component)
            }

            LaunchedEffect(Unit) {
                shakeDetector.shakeEvents.collect {
                    appThemeState.value = !appThemeState.value
                }
            }
        }
    }
}