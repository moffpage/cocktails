package kz.grandera.vlifetesttaskapp.resources

import androidx.compose.ui.graphics.vector.ImageVector
import kz.grandera.vlifetesttaskapp.resources.icons.Beer
import kz.grandera.vlifetesttaskapp.resources.icons.Champaign
import kz.grandera.vlifetesttaskapp.resources.icons.Cocktail
import kz.grandera.vlifetesttaskapp.resources.icons.Cocoa
import kz.grandera.vlifetesttaskapp.resources.icons.Coffee
import kz.grandera.vlifetesttaskapp.resources.icons.Liqueur
import kz.grandera.vlifetesttaskapp.resources.icons.Other
import kz.grandera.vlifetesttaskapp.resources.icons.Punch
import kz.grandera.vlifetesttaskapp.resources.icons.RegularDrink
import kz.grandera.vlifetesttaskapp.resources.icons.Shake
import kz.grandera.vlifetesttaskapp.resources.icons.Shot
import kz.grandera.vlifetesttaskapp.resources.icons.SoftDrink
import kotlin.collections.List as ____KtList

public object Icons

private var __All: ____KtList<ImageVector>? = null

public val Icons.All: ____KtList<ImageVector>
  get() {
    if (__All != null) {
      return __All!!
    }
    __All= listOf(Coffee, Cocktail, Cocoa, SoftDrink, Shot, Beer, Champaign, RegularDrink, Other,
        Shake, Liqueur, Punch)
    return __All!!
  }
