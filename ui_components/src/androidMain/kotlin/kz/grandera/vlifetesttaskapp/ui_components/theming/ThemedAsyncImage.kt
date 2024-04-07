package kz.grandera.vlifetesttaskapp.ui_components.theming

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.runtime.Composable
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.Image

import coil.compose.rememberAsyncImagePainter

@Composable
public fun ThemedAsyncImage(
    modifier: Modifier = Modifier,
    model: Any?,
    lightPlaceholderPainter: Painter,
    darkPlaceholderPainter: Painter,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = FilterQuality.High,
    contentDescription: String? = null
) {
    val themedPainter = rememberAsyncImagePainter(
        model = model,
        error = if (MaterialTheme.colors.isLight) lightPlaceholderPainter else darkPlaceholderPainter,
        filterQuality = filterQuality
    )

    Image(
        modifier = modifier,
        alpha = alpha,
        painter = themedPainter,
        alignment = alignment,
        colorFilter = colorFilter,
        contentScale = contentScale,
        contentDescription = contentDescription,
    )
}
