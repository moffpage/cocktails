package kz.grandera.vlifetesttaskapp.ui.cocktails.root

import androidx.compose.ui.Modifier
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.material.ExperimentalMaterialApi

import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation

import kz.grandera.vlifetesttaskapp.ui.cocktails.list.CocktailsListContent
import kz.grandera.vlifetesttaskapp.ui.cocktails.details.CocktailDetailsContent
import kz.grandera.vlifetesttaskapp.features.cocktails.root.component.CocktailsComponent
import kz.grandera.vlifetesttaskapp.features.cocktails.root.component.CocktailsComponent.Child

@Composable
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
public fun CocktailsContent(
    modifier: Modifier = Modifier,
    component: CocktailsComponent
) {
    val childStack by component.model.subscribeAsState()
    Children(
        stack = childStack,
        modifier = modifier,
        animation = stackAnimation(animator = fade() + scale())
    ) { child ->
        when (val childInstance = child.instance) {
            is Child.CocktailsList -> CocktailsListContent(
                component = childInstance.component
            )
            is Child.CocktailDetails -> CocktailDetailsContent(
                component = childInstance.component
            )
        }
    }
}