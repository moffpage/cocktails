package kz.grandera.vlifetesttaskapp.android.activity

import android.os.Bundle

import androidx.core.view.WindowCompat
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.SideEffect
import androidx.compose.material.Surface
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.google.accompanist.systemuicontroller.rememberSystemUiController

import com.arkivanov.decompose.defaultComponentContext

import kz.grandera.vlifetesttaskapp.ui.root.CocktailsContent
import kz.grandera.vlifetesttaskapp.android.theming.VlifeTestTaskAppTheme
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent
import kz.grandera.vlifetesttaskapp.features.root.component.cocktailsComponent

class VlifeTestTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val component: CocktailsComponent = cocktailsComponent(
            componentContext = defaultComponentContext()
        )

        setContent {
            val applyDarkTheme = isSystemInDarkTheme()
            val systemUiController = rememberSystemUiController()

            VlifeTestTaskAppTheme(applyDarkTheme = applyDarkTheme) {
                val systemBarsColor = Color.Transparent

                Surface {
                    CocktailsContent(component = component)
                }

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = systemBarsColor,
                        darkIcons = !applyDarkTheme
                    )
                }
            }
        }
    }
}