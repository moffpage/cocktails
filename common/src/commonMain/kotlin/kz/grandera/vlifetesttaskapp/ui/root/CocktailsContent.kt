package kz.grandera.vlifetesttaskapp.ui.root

import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.ExperimentalSharedTransitionApi

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.experimental.stack.ChildStack
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.PredictiveBackParams
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.stackAnimation

import kz.grandera.vlifetesttaskapp.ui.list.CocktailsListContent
import kz.grandera.vlifetesttaskapp.ui.details.CocktailDetailsContent
import kz.grandera.vlifetesttaskapp.ui.animation.CombinedSharedTransitionScope
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent.Child

@Composable
@ExperimentalMaterialApi
@ExperimentalDecomposeApi
@ExperimentalSharedTransitionApi
public fun CocktailsContent(
    component: CocktailsComponent,
    modifier: Modifier = Modifier
) {
    SharedTransitionLayout(modifier = modifier) {
        ChildStack(
            stack = component.model,
            animation = stackAnimation(
                animator = fade() + scale(),
                predictiveBackParams = {
                    PredictiveBackParams(
                        onBack = { component.onBackInvoked() },
                        animatable = { null },
                        backHandler = component.backHandler
                    )
                }
            )
        ) { child ->
            val sharedTransitionScope = CombinedSharedTransitionScope(
                visibilityScope = this,
                sharedTransitionScope = this@SharedTransitionLayout
            )
            when (val childInstance = child.instance) {
                is Child.CocktailsList -> CocktailsListContent(
                    component = childInstance.component,
                    sharedTransitionScope = sharedTransitionScope
                )

                is Child.CocktailDetails -> CocktailDetailsContent(
                    component = childInstance.component,
                    sharedTransitionScope = sharedTransitionScope
                )
            }
        }
    }
}