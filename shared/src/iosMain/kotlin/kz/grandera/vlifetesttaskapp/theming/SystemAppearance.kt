package kz.grandera.vlifetesttaskapp.theming

import platform.UIKit.UIApplication
import platform.UIKit.setStatusBarStyle
import platform.UIKit.UIStatusBarStyleLightContent
import platform.UIKit.UIStatusBarStyleDarkContent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
internal actual fun SystemAppearance(darkSystemBars: Boolean) {
    LaunchedEffect(darkSystemBars) {
        UIApplication.sharedApplication.setStatusBarStyle(
            statusBarStyle =  if (darkSystemBars) UIStatusBarStyleDarkContent else UIStatusBarStyleLightContent
        )
    }
}