package kz.grandera.vlifetesttaskapp.features.root.component

import kz.grandera.vlifetesttaskapp.core.componentcontext.AppComponentContext
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponentImpl

public fun cocktailsListComponentFactory(componentContext: AppComponentContext): CocktailsListComponent =
    CocktailsListComponentImpl(componentContext = componentContext)