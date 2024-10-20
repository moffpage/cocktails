package kz.grandera.vlifetesttaskapp.ui_components.segment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.material.Surface
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth

@Composable
public fun SegmentedControl(
    selected: ((index: Int) -> Boolean),
    titles: List<String>,
    onSegmentClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val tab: @Composable RowScope.(
        text: String,
        selected: Boolean,
        onClick: () -> Unit,
    ) -> Unit = {text, isSelected, onClick ->
        val tabBackgroundColor = if (isSelected) {
            MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.surface
        }
        Surface(
            modifier = Modifier
                .weight(weight = 1f)
                .clip(shape = CircleShape)
                .clickable(onClick = onClick),
            color = tabBackgroundColor,
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }

    Surface(
        modifier = modifier,
        shape = CircleShape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 2.dp)
                .clip(shape = CircleShape),
        ) {
            for ((index, title) in titles.withIndex()) {
                tab(title, selected(index)) { onSegmentClick(index) }
            }
        }
    }
}