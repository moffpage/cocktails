package kz.grandera.vlifetesttaskapp.di.module

import org.koin.dsl.module

import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponentImpl

internal val componentsModule = module {
    single {
        CocktailsListComponent.Factory { componentContext ->
            CocktailsListComponentImpl(
                componentContext = componentContext
            )
        }
    }
}