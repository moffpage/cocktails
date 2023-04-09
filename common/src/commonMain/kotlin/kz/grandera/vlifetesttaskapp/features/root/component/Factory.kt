package kz.grandera.vlifetesttaskapp.features.root.component

import com.arkivanov.decompose.ComponentContext

public fun cocktailsComponent(componentContext: ComponentContext): CocktailsComponent =
    CocktailsComponentImpl(componentContext = componentContext)