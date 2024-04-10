package kz.grandera.vlifetesttaskapp.resources.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kz.grandera.vlifetesttaskapp.resources.Icons

public val Icons.SoftDrink: ImageVector
    get() {
        if (_softDrink != null) {
            return _softDrink!!
        }
        _softDrink = Builder(name = "SoftDrink", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(6.0f, 6.0f)
                horizontalLineTo(18.0f)
                lineTo(16.4f, 22.0f)
                horizontalLineTo(7.6f)
                lineTo(6.0f, 6.0f)
                close()
                moveTo(7.76f, 7.6f)
                lineTo(9.04f, 20.4f)
                horizontalLineTo(9.84f)
                lineTo(8.744f, 9.472f)
                curveTo(9.6f, 9.2f, 10.712f, 9.112f, 11.6f, 10.0f)
                curveTo(12.848f, 11.248f, 15.064f, 10.552f, 16.0f, 10.184f)
                lineTo(16.24f, 7.6f)
                horizontalLineTo(7.76f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(11.5f, 11.0f)
                lineTo(12.719f, 4.5f)
                lineTo(13.0f, 3.0f)
                lineTo(16.0f, 2.0f)
                verticalLineTo(3.5f)
                lineTo(14.0f, 4.11f)
                lineTo(13.0f, 11.0f)
                horizontalLineTo(11.5f)
                close()
            }
        }
        .build()
        return _softDrink!!
    }

private var _softDrink: ImageVector? = null
