package kz.grandera.vlifetesttaskapp.di.module

import org.koin.dsl.module
import org.koin.core.module.Module

import kz.grandera.vlifetesttaskapp.api.cocktails.CocktailsApi
import kz.grandera.vlifetesttaskapp.api.cocktails.CocktailsApiClient

internal val networkModule: Module = module {
    single<CocktailsApi> { CocktailsApiClient(delegate = get()) }
}