package kz.grandera.vlifetesttaskapp.android.activity

import android.os.Bundle
import android.graphics.Color

import androidx.compose.material.Surface
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

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
            VlifeTestTaskAppTheme(
                applyDarkTheme = isSystemInDarkTheme()
            ) {
                Surface {
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