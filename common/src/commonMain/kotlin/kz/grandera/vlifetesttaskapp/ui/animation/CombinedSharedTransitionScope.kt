package kz.grandera.vlifetesttaskapp.ui.animation

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