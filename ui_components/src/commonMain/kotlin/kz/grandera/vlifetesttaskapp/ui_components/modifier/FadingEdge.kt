package kz.grandera.vlifetesttaskapp.ui_components.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

public fun Modifier.verticalFadingEdge(
    startY: Float,
    endY: Float,
    startColor: Color,
    endColor: Color,
): Modifier = fadingEdge(
    isVertical = true,
    startCoordinate = startY,
    endCoordinate = endY,
    startColor = startColor,
    endColor = endColor,
)

public fun Modifier.fadingEdge(
    isVertical: Boolean = true,
    startCoordinate: Float,
    endCoordinate: Float,
    startColor: Color,
    endColor: Color,
): Modifier = this.then(
    other = Modifier.drawWithContent {
        val colors = listOf(startColor, endColor)

        drawContent()

        if (isVertical) {
            drawRect(
                brush = Brush.verticalGradient(
                    colors = colors,
                    startY = startCoordinate,
                    endY = endCoordinate
                )
            )
        } else {
            drawRect(
                brush = Brush.horizontalGradient(
                    colors = colors,
                    startX = startCoordinate,
                    endX = endCoordinate
                )
            )
        }
    }
)