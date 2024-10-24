package kz.grandera.vlifetesttaskapp.android.activity

import android.os.Bundle
import android.graphics.Color
import android.hardware.SensorManager

import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import org.koin.core.scope.Scope
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope

import com.squareup.seismic.ShakeDetector

import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi

import kz.grandera.vlifetesttaskapp.ui.root.CocktailsContent
import kz.grandera.vlifetesttaskapp.core.componentcontext.wrapComponentContext
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent
import kz.grandera.vlifetesttaskapp.theming.SystemAppearance
import kz.grandera.vlifetesttaskapp.ui_components.theming.AppTheme
import kz.grandera.vlifetesttaskapp.ui_components.theming.LocalAppTheme
import kz.grandera.vlifetesttaskapp.ui_components.theming.VlifeTestTaskAppTheme

class VlifeTestTaskActivity : ComponentActivity(), AndroidScopeComponent {
    override val scope: Scope by activityScope()

    private val componentFactory by scope.inject<CocktailsComponent.Factory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scope.declare(this)

        val component = componentFactory.create(
            componentContext = wrapComponentContext(
                context = defaultComponentContext(),
                parentScopeId = scope.id
            )
        )

        enableEdgeToEdge(
            statusBarStyle = systemBarsStyle,
            navigationBarStyle = systemBarsStyle
        )

        setContent {
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
                val appTheme by appThemeState
                val shakeDetector = remember {
                    ShakeDetector {
                        appThemeState.value = !appThemeState.value
                    }
                }
                val sensorManager = LocalContext.current.getSystemService(SENSOR_SERVICE) as SensorManager
                SideEffect {
                    shakeDetector.start(sensorManager, SensorManager.SENSOR_DELAY_GAME)
                }
                VlifeTestTaskAppTheme(appTheme = appTheme) {
                    @OptIn(ExperimentalDecomposeApi::class)
                    CocktailsContent(component = component)
                    SystemAppearance(darkSystemBars = appTheme != AppTheme.Dark)
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