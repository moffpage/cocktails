package kz.grandera.vlifetesttaskapp.ui.root

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable

import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation

import kz.grandera.vlifetesttaskapp.ui.list.CocktailsListContent
import kz.grandera.vlifetesttaskapp.ui.details.CocktailDetailsContent
import kz.grandera.vlifetesttaskapp.ui.timetravel.TimeTravelClientContent
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent.Child

@Composable
public fun CocktailsContent(component: CocktailsComponent) {
    val childStack by component.model.subscribeAsState()
    Children(
        stack = childStack,
        animation = stackAnimation(animator = fade() + scale())
    ) { child ->
        when (val childInstance = child.instance) {
            is Child.CocktailsList -> CocktailsListContent(
                component = childInstance.component
            )
            is Child.CocktailDetails -> CocktailDetailsContent(
                component = childInstance.component
            )
            is Child.TimeTravelLookOver -> TimeTravelClientContent(
                component = childInstance.component
            )
        }
    }
}