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

public val Icons.Coffee: ImageVector
    get() {
        if (_coffee != null) {
            return _coffee!!
        }
        _coffee = Builder(name = "Coffee", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFECBE43)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(2.0f, 21.0f)
                horizontalLineTo(20.0f)
                verticalLineTo(19.0f)
                horizontalLineTo(2.0f)
                moveTo(20.0f, 8.0f)
                horizontalLineTo(18.0f)
                verticalLineTo(5.0f)
                horizontalLineTo(20.0f)
                moveTo(20.0f, 3.0f)
                horizontalLineTo(4.0f)
                verticalLineTo(13.0f)
                curveTo(4.0f, 14.061f, 4.421f, 15.078f, 5.172f, 15.828f)
                curveTo(5.922f, 16.579f, 6.939f, 17.0f, 8.0f, 17.0f)
                horizontalLineTo(14.0f)
                curveTo(15.061f, 17.0f, 16.078f, 16.579f, 16.828f, 15.828f)
                curveTo(17.579f, 15.078f, 18.0f, 14.061f, 18.0f, 13.0f)
                verticalLineTo(10.0f)
                horizontalLineTo(20.0f)
                curveTo(20.53f, 10.0f, 21.039f, 9.789f, 21.414f, 9.414f)
                curveTo(21.789f, 9.039f, 22.0f, 8.53f, 22.0f, 8.0f)
                verticalLineTo(5.0f)
                curveTo(22.0f, 3.89f, 21.1f, 3.0f, 20.0f, 3.0f)
                close()
            }
        }
        .build()
        return _coffee!!
    }

private var _coffee: ImageVector? = null
