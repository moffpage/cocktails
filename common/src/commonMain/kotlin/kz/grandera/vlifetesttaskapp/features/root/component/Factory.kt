package kz.grandera.vlifetesttaskapp.features.root.component

import com.arkivanov.decompose.ComponentContext

import kz.grandera.vlifetesttaskapp.utils.FileManager
import kz.grandera.vlifetesttaskapp.utils.PlatformContext

public fun cocktailsComponent(
    fileManager: FileManager,
    platformContext: PlatformContext,
    componentContext: ComponentContext
): CocktailsComponent =
    CocktailsComponentImpl(
        fileManager = fileManager,
        platformContext = platformContext,
        componentContext = componentContext
    )