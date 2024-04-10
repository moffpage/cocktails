package kz.grandera.vlifetesttaskapp.ui_components.resources.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kz.grandera.vlifetesttaskapp.ui_components.resources.Icons

public val Icons.Error: ImageVector
    get() {
        if (_error != null) {
            return _error!!
        }
        _error = Builder(name = "Error", defaultWidth = 49.0.dp, defaultHeight = 48.0.dp,
                viewportWidth = 49.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFFB3261E)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(22.5f, 31.0f)
                curveTo(22.5f, 30.448f, 22.948f, 30.0f, 23.5f, 30.0f)
                horizontalLineTo(25.5f)
                curveTo(26.052f, 30.0f, 26.5f, 30.448f, 26.5f, 31.0f)
                verticalLineTo(33.0f)
                curveTo(26.5f, 33.552f, 26.052f, 34.0f, 25.5f, 34.0f)
                horizontalLineTo(23.5f)
                curveTo(22.948f, 34.0f, 22.5f, 33.552f, 22.5f, 33.0f)
                verticalLineTo(31.0f)
                close()
                moveTo(22.5f, 15.0f)
                curveTo(22.5f, 14.448f, 22.948f, 14.0f, 23.5f, 14.0f)
                horizontalLineTo(25.5f)
                curveTo(26.052f, 14.0f, 26.5f, 14.448f, 26.5f, 15.0f)
                verticalLineTo(25.0f)
                curveTo(26.5f, 25.552f, 26.052f, 26.0f, 25.5f, 26.0f)
                horizontalLineTo(23.5f)
                curveTo(22.948f, 26.0f, 22.5f, 25.552f, 22.5f, 25.0f)
                verticalLineTo(15.0f)
                close()
                moveTo(24.5f, 4.0f)
                curveTo(13.44f, 4.0f, 4.5f, 13.0f, 4.5f, 24.0f)
                curveTo(4.5f, 29.304f, 6.607f, 34.391f, 10.358f, 38.142f)
                curveTo(12.215f, 39.999f, 14.42f, 41.472f, 16.846f, 42.478f)
                curveTo(19.273f, 43.483f, 21.874f, 44.0f, 24.5f, 44.0f)
                curveTo(29.804f, 44.0f, 34.891f, 41.893f, 38.642f, 38.142f)
                curveTo(42.393f, 34.391f, 44.5f, 29.304f, 44.5f, 24.0f)
                curveTo(44.5f, 21.374f, 43.983f, 18.773f, 42.978f, 16.346f)
                curveTo(41.972f, 13.92f, 40.499f, 11.715f, 38.642f, 9.858f)
                curveTo(36.785f, 8.001f, 34.58f, 6.528f, 32.154f, 5.522f)
                curveTo(29.727f, 4.517f, 27.126f, 4.0f, 24.5f, 4.0f)
                close()
                moveTo(24.5f, 40.0f)
                curveTo(20.257f, 40.0f, 16.187f, 38.314f, 13.186f, 35.314f)
                curveTo(10.186f, 32.313f, 8.5f, 28.243f, 8.5f, 24.0f)
                curveTo(8.5f, 19.757f, 10.186f, 15.687f, 13.186f, 12.686f)
                curveTo(16.187f, 9.686f, 20.257f, 8.0f, 24.5f, 8.0f)
                curveTo(28.743f, 8.0f, 32.813f, 9.686f, 35.814f, 12.686f)
                curveTo(38.814f, 15.687f, 40.5f, 19.757f, 40.5f, 24.0f)
                curveTo(40.5f, 28.243f, 38.814f, 32.313f, 35.814f, 35.314f)
                curveTo(32.813f, 38.314f, 28.743f, 40.0f, 24.5f, 40.0f)
                close()
            }
        }
        .build()
        return _error!!
    }

private var _error: ImageVector? = null
