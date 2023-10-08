package kz.grandera.vlifetesttaskapp.ui.root

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.runtime.Composable
import androidx.compose.material.ExperimentalMaterialApi

import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation

import kz.grandera.vlifetesttaskapp.ui.cocktails.root.CocktailsContent

import kz.grandera.vlifetesttaskapp.ui.theming.VlifeTestTaskAppTheme
import kz.grandera.vlifetesttaskapp.features.root.component.VlifeTestTaskAppComponent
import kz.grandera.vlifetesttaskapp.features.root.component.VlifeTestTaskAppComponent.Child
import kz.grandera.vlifetesttaskapp.time_travel_client.TimeTravelClientContent

@Composable
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
public fun VlifeTestTaskAppContent(component: VlifeTestTaskAppComponent) {
    Children(
        stack = component.model,
        animation = stackAnimation(animator = fade() + scale())
    ) { child ->
        when (val childInstance = child.instance) {
            is Child.Cocktails -> CocktailsContent(
                component = childInstance.component
            )
            is Child.TimeTravelLookOver -> {
                VlifeTestTaskAppTheme(applyDarkTheme = false) {
                    TimeTravelClientContent(
                        component = childInstance.component
                    )
                }
            }
        }
    }
}