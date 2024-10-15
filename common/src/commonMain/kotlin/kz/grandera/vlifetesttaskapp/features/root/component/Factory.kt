package kz.grandera.vlifetesttaskapp.features.root.component

import kz.grandera.vlifetesttaskapp.core.componentcontext.AppComponentContext

public fun cocktailsComponentFactory(componentContext: AppComponentContext): CocktailsComponent =
    CocktailsComponentImpl(componentContext = componentContext)