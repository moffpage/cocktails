package kz.grandera.vlifetesttaskapp.di.module

import org.koin.dsl.module

import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponentImpl
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponentImpl

internal val componentsModule = module {
    single {
        CocktailsListComponent.Factory { componentContext, onShowCocktail ->
            CocktailsListComponentImpl(
                onShowCocktail = onShowCocktail,
                componentContext = componentContext
            )
        }
    }

    single {
        CocktailDetailsComponent.Factory { onBack, cocktailId, componentContext ->
            CocktailDetailsComponentImpl(
                id = cocktailId,
                onNavigateBack = onBack,
                componentContext = componentContext
            )
        }
    }
}