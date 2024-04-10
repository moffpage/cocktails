package kz.grandera.vlifetesttaskapp.ui.root

import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.material.ExperimentalMaterialApi

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.androidPredictiveBackAnimatable

import kz.grandera.vlifetesttaskapp.ui.list.CocktailsListContent
import kz.grandera.vlifetesttaskapp.ui.details.CocktailDetailsContent
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent.Child

@Composable
@ExperimentalMaterialApi
@ExperimentalDecomposeApi
public fun CocktailsContent(
    modifier: Modifier = Modifier,
    component: CocktailsComponent
) {
    Children(
        stack = component.model,
        modifier = modifier,
        animation = predictiveBackAnimation(
            backHandler = component.backHandler,
            fallbackAnimation = stackAnimation(animator = fade() + scale()),
            selector = { initialBackEvent, _, _ ->
                androidPredictiveBackAnimatable(
                    initialBackEvent = initialBackEvent
                )
            },
            onBack = { component.onBackInvoked() }
        )
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