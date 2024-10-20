package kz.grandera.vlifetesttaskapp.ui_components.chip

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.runtime.Composable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.Surface
import androidx.compose.material.MaterialTheme
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.Arrangement

@Composable
public fun Chip(
    text: String,
    iconPainter: Painter?,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    surfaceColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = if (iconPainter != null) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.secondary
    }
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = surfaceColor,
        contentColor = contentColor
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .widthIn(60.dp, 200.dp)
                .height(height = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    strokeCap = StrokeCap.Butt
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
                ) {
                    if (iconPainter != null) {
                        Icon(
                            painter = iconPainter,
                            contentDescription = text
                        )
                    }

                    Text(
                        text = text,
                        style = MaterialTheme.typography.h4
                            .copy(textAlign = TextAlign.Center)
                    )
                }
            }
        }
    }
}