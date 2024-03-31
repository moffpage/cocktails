package kz.grandera.vlifetesttaskapp.ui_components.theming.shapes

import androidx.compose.material.Shapes

public val ComposeShapes: Shapes = with(ShapesFactory) {
    Shapes(
        small = small.toComposeRoundedCornerShape(),
        medium = medium.toComposeRoundedCornerShape(),
        large = large.toComposeRoundedCornerShape()
    )
}