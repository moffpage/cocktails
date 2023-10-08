package kz.grandera.vlifetesttaskapp.features.root.component

import com.arkivanov.decompose.ComponentContext

public fun cocktailsComponentFactory(componentContext: ComponentContext): CocktailsComponent =
    CocktailsComponentImpl(componentContext = componentContext)