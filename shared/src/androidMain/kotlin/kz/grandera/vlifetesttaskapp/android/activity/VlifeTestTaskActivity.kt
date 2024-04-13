package kz.grandera.vlifetesttaskapp.android.activity

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.hardware.SensorManager

import androidx.core.view.WindowInsetsControllerCompat
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.squareup.seismic.ShakeDetector

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.defaultComponentContext

import kz.grandera.vlifetesttaskapp.ui.root.CocktailsContent
import kz.grandera.vlifetesttaskapp.ui_components.theming.VlifeTestTaskAppTheme
import kz.grandera.vlifetesttaskapp.features.root.component.cocktailsComponentFactory

class VlifeTestTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = cocktailsComponentFactory(
            componentContext = defaultComponentContext()
        )

        enableEdgeToEdge(
            statusBarStyle = systemBarsStyle,
            navigationBarStyle = systemBarsStyle
        )

        setContent {
            val isSystemInDarkTheme = isSystemInDarkTheme()
            val applyingTheme = remember(isSystemInDarkTheme) {
                mutableStateOf(value = isSystemInDarkTheme)
            }
            CompositionLocalProvider(LocalAppTheme provides applyingTheme) {
                val view = LocalView.current
                val applyDarkTheme by applyingTheme
                val shakeDetector = remember {
                    ShakeDetector {
                        applyingTheme.value = !applyingTheme.value
                    }
                }
                val sensorManager = LocalContext.current.getSystemService(SENSOR_SERVICE) as SensorManager

                LaunchedEffect(applyDarkTheme) {
                    val window = (view.context as Activity).window
                    WindowInsetsControllerCompat(window, window.decorView).apply {
                        isAppearanceLightStatusBars = !applyDarkTheme
                        isAppearanceLightNavigationBars = !applyDarkTheme
                    }
                }


                SideEffect {
                    shakeDetector.start(sensorManager, SensorManager.SENSOR_DELAY_GAME)
                }

                VlifeTestTaskAppTheme(applyDarkTheme = applyDarkTheme) {
                    @OptIn(
                        ExperimentalMaterialApi::class,
                        ExperimentalDecomposeApi::class
                    )
                    CocktailsContent(component = component)
                }
            }
        }
    }

    private companion object {
        val systemBarsStyle = SystemBarStyle.auto(
            lightScrim = Color.TRANSPARENT,
            darkScrim = Color.TRANSPARENT
        )
    }
}

private val LocalAppTheme: ProvidableCompositionLocal<MutableState<Boolean>> =
    compositionLocalOf { mutableStateOf(value = false) }

