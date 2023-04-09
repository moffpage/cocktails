package kz.grandera.vlifetesttaskapp.di

import io.github.aakira.napier.Napier
import io.github.aakira.napier.DebugAntilog

import org.koin.core.context.startKoin

public fun initializeKoin() {
    startKoin {
        modules(modules = cocktailsModules)
    }
}

public fun enableLogging() {
    Napier.base(antilog = DebugAntilog())
}