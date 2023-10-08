package kz.grandera.vlifetesttaskapp.di.module

import org.koin.core.module.Module

import kz.grandera.vlifetesttaskapp.core.module.asyncModule
import kz.grandera.vlifetesttaskapp.core.module.httpClientModule

public val cocktailsModules: List<Module> = mutableListOf<Module>().apply {
    add(element = asyncModule)
    add(element = httpClientModule)
    add(element = networkModule)
}