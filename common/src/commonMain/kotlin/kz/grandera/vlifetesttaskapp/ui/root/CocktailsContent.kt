package kz.grandera.vlifetesttaskapp.ui.root

import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children

import kz.grandera.vlifetesttaskapp.ui.animation.backAnimation
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent

@Composable
@ExperimentalDecomposeApi
public fun CocktailsContent(
    modifier: Modifier = Modifier,
    component: CocktailsComponent
) {
    Children(
        stack = component.model,
        modifier = modifier,
        animation = backAnimation(
            backHandler = component.backHandler,
            onBack = { component.onBackInvoked() }
        )
    ) { child ->
        child.instance.Content()
    }
}