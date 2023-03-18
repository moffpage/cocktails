package kz.grandera.vlifetesttaskapp.di

import io.github.aakira.napier.Napier
import io.github.aakira.napier.DebugAntilog

public fun enableLogging() {
    Napier.base(antilog = DebugAntilog())
}