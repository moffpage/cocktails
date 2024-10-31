package kz.grandera.vlifetesttaskapp.ui_components.loading

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import org.jetbrains.compose.resources.ExperimentalResourceApi

import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import io.github.alexzhirkevich.compottie.rememberLottieComposition

import kz.grandera.vlifetesttaskapp.ui_components.Res
import kz.grandera.vlifetesttaskapp.ui_components.modifier.disableInput

@OptIn(ExperimentalResourceApi::class)
@Composable
public fun LoadingContent(
    modifier: Modifier = Modifier,
    alpha: Float = 0.5f,
    enableInput: Boolean = false,
    backgroundColor: Color = MaterialTheme.colorScheme.background
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor.copy(alpha = alpha))
            .disableInput(allow = enableInput)
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = rememberLottiePainter(
                composition = rememberLottieComposition(
                    spec = {
                        LottieCompositionSpec.JsonString(
                            jsonString = Res.readBytes(
                                path = "files/cocktail_animation.json"
                            ).decodeToString()
                        )
                    }
                ).value,
                iterations = Compottie.IterateForever
            ),
            contentDescription = null
        )
    }
}