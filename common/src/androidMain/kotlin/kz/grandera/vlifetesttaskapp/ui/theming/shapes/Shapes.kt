package kz.grandera.vlifetesttaskapp.ui.theming.shapes

import androidx.compose.material.Shapes

import kz.grandera.vlifetesttaskapp.theming.shapes.ShapesFactory

public val Shapes: Shapes = with(ShapesFactory) {
    Shapes(
        small = small.toComposeRoundedCornerShape(),
        medium = medium.toComposeRoundedCornerShape(),
        large = large.toComposeRoundedCornerShape()
    )
}