package kz.grandera.vlifetesttaskapp.di.module

import org.koin.core.module.Module

import kz.grandera.vlifetesttaskapp.core.module.asyncModule
import kz.grandera.vlifetesttaskapp.core.module.filesModule
import kz.grandera.vlifetesttaskapp.core.module.httpClientModule
import kz.grandera.vlifetesttaskapp.core.build_config.isDebug
import kz.grandera.vlifetesttaskapp.time_travel_client.module.timeTravelModule

public val cocktailsModules: List<Module> = mutableListOf<Module>().apply {
    add(element = asyncModule)
    add(element = filesModule)
    add(element = httpClientModule)
    add(element = networkModule)

    if (isDebug) {
        add(element = timeTravelModule)
    }
}