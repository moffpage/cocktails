package kz.grandera.vlifetesttaskapp.resources.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kz.grandera.vlifetesttaskapp.resources.Icons

public val Icons.Cocoa: ImageVector
    get() {
        if (_cocoa != null) {
            return _cocoa!!
        }
        _cocoa = Builder(name = "Cocoa", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFECBE43)),
                        strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Round,
                        strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(6.293f, 1.622f)
                    lineTo(8.265f, 1.293f)
                    curveTo(8.538f, 1.248f, 8.795f, 1.432f, 8.841f, 1.704f)
                    lineTo(9.17f, 3.677f)
                    curveTo(9.215f, 3.949f, 9.032f, 4.207f, 8.759f, 4.252f)
                    lineTo(6.786f, 4.581f)
                    curveTo(6.514f, 4.627f, 6.256f, 4.443f, 6.211f, 4.17f)
                    lineTo(5.882f, 2.198f)
                    curveTo(5.836f, 1.925f, 6.02f, 1.668f, 6.293f, 1.622f)
                    close()
                    moveTo(11.625f, 3.922f)
                    lineTo(13.076f, 1.89f)
                    curveTo(13.236f, 1.665f, 13.548f, 1.613f, 13.773f, 1.774f)
                    lineTo(15.806f, 3.225f)
                    curveTo(16.03f, 3.385f, 16.082f, 3.697f, 15.922f, 3.922f)
                    lineTo(14.471f, 5.954f)
                    curveTo(14.31f, 6.179f, 13.998f, 6.231f, 13.773f, 6.071f)
                    lineTo(11.741f, 4.62f)
                    curveTo(11.516f, 4.459f, 11.464f, 4.147f, 11.625f, 3.922f)
                    close()
                }
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
        }
        .build()
        return _cocoa!!
    }

private var _cocoa: ImageVector? = null
