package kz.grandera.vlifetesttaskapp.ui_components.loading

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import org.jetbrains.compose.resources.ExperimentalResourceApi

import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition

import kz.grandera.vlifetesttaskapp.ui_components.Res
import kz.grandera.vlifetesttaskapp.ui_components.modifier.disableInput

@OptIn(ExperimentalResourceApi::class)
@Composable
public fun LoadingContent(
    modifier: Modifier = Modifier,
    alpha: Float = 0.5f,
    enableInput: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.background
) {
    var jsonString by remember { mutableStateOf(value = "") }

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.JsonString(
            jsonString = jsonString
        )
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Int.MAX_VALUE
    )

    LaunchedEffect(Unit) {
        jsonString = Res.readBytes("files/cocktail_animation.json")
            .decodeToString()
    }

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
            composition = composition
        )
    }
}