package kz.grandera.vlifetesttaskapp.ui_components.theming.shapes

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape as ComposeRoundedCornerShape

internal fun RoundedCornerShape.toComposeRoundedCornerShape(): ComposeRoundedCornerShape =
    ComposeRoundedCornerShape(size = cornerRadius.dp)