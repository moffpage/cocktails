package kz.grandera.vlifetesttaskapp.ui.loading

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.animateLottieCompositionAsState

import kz.grandera.vlifetesttaskapp.R
import kz.grandera.vlifetesttaskapp.utils.disableInput

@Composable
internal fun LoadingContent(
    modifier: Modifier = Modifier,
    alpha: Float = 0.5f,
    enableInput: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.background
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            resId = R.raw.cocktail_animation
        )
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Int.MAX_VALUE
    )

    Box(
        modifier = modifier
            .background(color = backgroundColor.copy(alpha = alpha))
            .fillMaxSize()
            .disableInput(allow = enableInput)
    ) {
        LottieAnimation(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .matchParentSize(),
            progress = { progress },
            composition = composition,
        )
    }
}