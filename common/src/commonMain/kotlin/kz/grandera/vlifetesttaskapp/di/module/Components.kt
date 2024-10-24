package kz.grandera.vlifetesttaskapp.di.module

import org.koin.dsl.module

import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponentImpl
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponentImpl
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponentImpl

internal val componentsModule = module {
    single {
        CocktailsComponent.Factory { componentContext ->
            CocktailsComponentImpl(
                componentContext = componentContext
            )
        }
    }

    single {
        CocktailsListComponent.Factory { componentContext ->
            CocktailsListComponentImpl(
                componentContext = componentContext
            )
        }
    }

    single {
        CocktailDetailsComponent.Factory { cocktailId, componentContext ->
            CocktailDetailsComponentImpl(
                id = cocktailId,
                componentContext = componentContext
            )
        }
    }
}