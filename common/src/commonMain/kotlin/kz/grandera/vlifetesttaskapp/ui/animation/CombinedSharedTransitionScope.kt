package kz.grandera.vlifetesttaskapp.ui.animation

import androidx.compose.ui.composed
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Stable
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi

@Stable
@ExperimentalSharedTransitionApi
internal class CombinedSharedTransitionScope(
    private val visibilityScope: AnimatedVisibilityScope,
    private val sharedTransitionScope: SharedTransitionScope
) : SharedTransitionScope by sharedTransitionScope,
    AnimatedVisibilityScope by visibilityScope
{
    fun Modifier.sharedBounds(
        key: Any,
        renderInOverlayDuringTransition: Boolean = false
    ): Modifier = composed {
        sharedBounds(
            sharedContentState = rememberSharedContentState(key = key),
            animatedVisibilityScope = this@CombinedSharedTransitionScope,
            renderInOverlayDuringTransition = renderInOverlayDuringTransition
        )
    }
}