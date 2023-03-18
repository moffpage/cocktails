package kz.grandera.vlifetesttaskapp.ui.theming.shapes

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape as ComposeRoundedCornerShape

import kz.grandera.vlifetesttaskapp.theming.shapes.RoundedCornerShape

internal fun RoundedCornerShape.toComposeRoundedCornerShape(): ComposeRoundedCornerShape =
    ComposeRoundedCornerShape(size = cornerRadius.dp)