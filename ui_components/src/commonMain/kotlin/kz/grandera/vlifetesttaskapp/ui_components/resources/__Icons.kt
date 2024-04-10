package kz.grandera.vlifetesttaskapp.ui_components.resources

import androidx.compose.ui.graphics.vector.ImageVector
import kz.grandera.vlifetesttaskapp.ui_components.resources.icons.Error
import kotlin.collections.List as ____KtList

public object Icons

private var __All: ____KtList<ImageVector>? = null

public val Icons.All: ____KtList<ImageVector>
  get() {
    if (__All != null) {
      return __All!!
    }
    __All= listOf(Error)
    return __All!!
  }
