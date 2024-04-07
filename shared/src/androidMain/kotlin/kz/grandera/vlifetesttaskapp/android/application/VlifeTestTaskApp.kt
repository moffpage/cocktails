package kz.grandera.vlifetesttaskapp.android.application

import android.app.Application

import io.github.aakira.napier.Napier
import io.github.aakira.napier.DebugAntilog

import org.koin.core.logger.Level
import org.koin.android.ext.koin.androidLogger
import org.koin.android.ext.koin.androidContext

import kz.grandera.vlifetesttaskapp.core.build_config.isDebug
import kz.grandera.vlifetesttaskapp.di.initializeKoin

internal class VlifeTestTaskApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (isDebug) {
            Napier.base(antilog = DebugAntilog())
        }

        initializeKoin {
            androidLogger(level = if (isDebug) Level.DEBUG else Level.NONE)
            androidContext(androidContext = this@VlifeTestTaskApp)
        }
    }
}