package kz.grandera.vlifetesttaskapp.theming

import android.app.Activity

import androidx.core.view.WindowInsetsControllerCompat
import androidx.compose.ui.platform.LocalView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
internal actual fun SystemAppearance(darkSystemBars: Boolean) {
    val view = LocalView.current
    LaunchedEffect(darkSystemBars) {
        val window = (view.context as Activity).window
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = darkSystemBars
            isAppearanceLightNavigationBars = darkSystemBars
        }
    }
}