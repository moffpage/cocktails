package kz.grandera.vlifetesttaskapp.di.module

import org.koin.dsl.module

import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponentImpl

internal val componentsModule = module {
    single {
        CocktailsComponent.Factory { componentContext ->
            CocktailsComponentImpl(
                componentContext = componentContext
            )
        }
    }
}