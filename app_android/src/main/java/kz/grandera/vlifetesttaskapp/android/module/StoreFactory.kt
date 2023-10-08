package kz.grandera.vlifetesttaskapp.android.module

import org.koin.dsl.module
import org.koin.core.module.Module

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory

import kz.grandera.vlifetesttaskapp.core.build_config.isDebug

internal val storeFactoryModule: Module = module {
    single<StoreFactory> {
        if (isDebug) {
            LoggingStoreFactory(
                delegate = TimeTravelStoreFactory()
            )
        } else {
            DefaultStoreFactory()
        }
    }
}