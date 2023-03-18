package kz.grandera.vlifetesttaskapp.features.root.component

import com.arkivanov.decompose.ComponentContext

import kz.grandera.vlifetesttaskapp.utils.PlatformContext

public fun cocktailsComponent(
    platformContext: PlatformContext,
    componentContext: ComponentContext
): CocktailsComponent =
    CocktailsComponentImpl(
        platformContext = platformContext,
        componentContext = componentContext
    )