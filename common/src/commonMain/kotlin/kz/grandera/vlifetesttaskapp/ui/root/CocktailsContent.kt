package kz.grandera.vlifetesttaskapp.ui.root

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.material.ExperimentalMaterialApi

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.experimental.stack.ChildStack

import kz.grandera.vlifetesttaskapp.ui.list.CocktailsListContent
import kz.grandera.vlifetesttaskapp.ui.details.CocktailDetailsContent
import kz.grandera.vlifetesttaskapp.ui.animation.backAnimation
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent.Child

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@ExperimentalMaterialApi
@ExperimentalDecomposeApi
public fun CocktailsContent(
    modifier: Modifier = Modifier,
    component: CocktailsComponent
) {
    SharedTransitionLayout {
        ChildStack(
            stack = component.model,
            modifier = modifier,
            animation = backAnimation(
                backHandler = component.backHandler,
                onBack = { component.onBackInvoked() }
            )
        ) { child ->
            when (val childInstance = child.instance) {
                is Child.CocktailsList -> CocktailsListContent(
                    component = childInstance.component,
                    animatedVisibilityScope = this,
                )

                is Child.CocktailDetails -> CocktailDetailsContent(
                    component = childInstance.component,
                    animatedVisibilityScope = this,
                )
            }
        }
    }
}
