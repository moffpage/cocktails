package kz.grandera.vlifetesttaskapp.android.activity

import android.os.Bundle
import android.graphics.Color

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.material.Surface
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.arkivanov.decompose.defaultComponentContext

import kz.grandera.vlifetesttaskapp.shake.ShakeActionHandler
import kz.grandera.vlifetesttaskapp.ui.root.VlifeTestTaskAppContent
import kz.grandera.vlifetesttaskapp.ui.theming.VlifeTestTaskAppTheme
import kz.grandera.vlifetesttaskapp.features.root.component.VlifeTestTaskAppComponent
import kz.grandera.vlifetesttaskapp.features.root.component.vlifeTestTaskAppComponent

class VlifeTestTaskActivity : ComponentActivity() {
    private lateinit var component: VlifeTestTaskAppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.component = vlifeTestTaskAppComponent(
            componentContext = defaultComponentContext()
        )

        enableEdgeToEdge(
            statusBarStyle = systemBarsStyle,
            navigationBarStyle = systemBarsStyle
        )

        setContent {
            VlifeTestTaskAppTheme(
                applyDarkTheme = isSystemInDarkTheme()
            ) {
                Surface {
                    @OptIn(
                        ExperimentalMaterialApi::class,
                        ExperimentalComposeUiApi::class
                    )
                    VlifeTestTaskAppContent(
                        component = component
                    )
                }
            }
        }

        bindShakeAction()
    }

    private fun bindShakeAction() {
        ShakeActionHandler(
            context = applicationContext,
            detector = {
                component.showTimeTravelLookOver()
            }
        )
    }

    private companion object {
        val systemBarsStyle = SystemBarStyle.auto(
            lightScrim = Color.TRANSPARENT,
            darkScrim = Color.TRANSPARENT
        )
    }
}