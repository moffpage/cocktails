package kz.grandera.vlifetesttaskapp.di

import io.github.aakira.napier.Napier
import io.github.aakira.napier.DebugAntilog

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

import kz.grandera.vlifetesttaskapp.di.module.cocktailsModules

public fun initializeKoin(onInit: (KoinApplication.() -> Unit)? = null) {
    startKoin {
        onInit?.invoke(this)
        modules(modules = cocktailsModules)
    }
}

public fun enableLogging() {
    Napier.base(antilog = DebugAntilog())
}