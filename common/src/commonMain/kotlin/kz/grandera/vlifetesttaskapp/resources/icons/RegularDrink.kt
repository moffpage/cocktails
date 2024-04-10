package kz.grandera.vlifetesttaskapp.resources.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kz.grandera.vlifetesttaskapp.resources.Icons

public val Icons.RegularDrink: ImageVector
    get() {
        if (_regularDrink != null) {
            return _regularDrink!!
        }
        _regularDrink = Builder(name = "RegularDrink", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFECBE43)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(14.4f, 9.58f)
                curveTo(12.9f, 7.89f, 13.0f, 4.0f, 13.0f, 4.0f)
                horizontalLineTo(14.0f)
                verticalLineTo(2.0f)
                horizontalLineTo(10.0f)
                verticalLineTo(4.0f)
                horizontalLineTo(11.0f)
                curveTo(11.0f, 4.0f, 11.1f, 7.89f, 9.6f, 9.58f)
                curveTo(9.411f, 9.765f, 9.261f, 9.986f, 9.158f, 10.23f)
                curveTo(9.055f, 10.474f, 9.001f, 10.735f, 9.0f, 11.0f)
                verticalLineTo(20.0f)
                curveTo(9.0f, 20.53f, 9.211f, 21.039f, 9.586f, 21.414f)
                curveTo(9.961f, 21.789f, 10.47f, 22.0f, 11.0f, 22.0f)
                horizontalLineTo(13.0f)
                curveTo(13.53f, 22.0f, 14.039f, 21.789f, 14.414f, 21.414f)
                curveTo(14.789f, 21.039f, 15.0f, 20.53f, 15.0f, 20.0f)
                verticalLineTo(11.0f)
                curveTo(14.999f, 10.735f, 14.945f, 10.474f, 14.842f, 10.23f)
                curveTo(14.739f, 9.986f, 14.589f, 9.765f, 14.4f, 9.58f)
                close()
                moveTo(13.0f, 20.0f)
                horizontalLineTo(11.0f)
                verticalLineTo(11.0f)
                lineTo(11.1f, 10.91f)
                curveTo(11.462f, 10.482f, 11.764f, 10.008f, 12.0f, 9.5f)
                curveTo(12.236f, 10.008f, 12.538f, 10.482f, 12.9f, 10.91f)
                lineTo(13.0f, 11.0f)
                verticalLineTo(20.0f)
                close()
            }
        }
        .build()
        return _regularDrink!!
    }

private var _regularDrink: ImageVector? = null
