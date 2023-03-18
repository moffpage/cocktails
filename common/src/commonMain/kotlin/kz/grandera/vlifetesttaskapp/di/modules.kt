package kz.grandera.vlifetesttaskapp.di

import kotlin.coroutines.CoroutineContext

import org.koin.dsl.module
import org.koin.core.module.Module
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named

import io.ktor.client.HttpClient

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import com.arkivanov.mvikotlin.timetravel.export.TimeTravelExportSerializer

import kz.grandera.vlifetesttaskapp.api.httpClient
import kz.grandera.vlifetesttaskapp.api.cocktails.CocktailsApi
import kz.grandera.vlifetesttaskapp.api.cocktails.CocktailsApiClient
import kz.grandera.vlifetesttaskapp.async.ioDispatcher
import kz.grandera.vlifetesttaskapp.async.mainDispatcher
import kz.grandera.vlifetesttaskapp.utils.isDebug
import kz.grandera.vlifetesttaskapp.utils.PlatformContext
import kz.grandera.vlifetesttaskapp.utils.timeTravelExportSerializer
import kz.grandera.vlifetesttaskapp.features.root.component.cocktailsComponent
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent

public fun initializeKoin() {
    startKoin {
        modules(cocktailsModules)
    }
}

internal val coreModule: Module = module {
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

internal val asyncModule: Module = module {
    single<CoroutineContext>(qualifier = named(name = "IO")) { ioDispatcher }
    single<CoroutineContext>(qualifier = named(name = "Main")) { mainDispatcher }
}

internal val networkModule: Module = module {
    single<HttpClient> { httpClient(enableLogging = isDebug) }
    single<CocktailsApi> { CocktailsApiClient(delegate = get()) }
}

internal val timeTravelModule: Module = module {
    single<TimeTravelExportSerializer> { timeTravelExportSerializer }
}

public val cocktailsModules: List<Module> = mutableListOf<Module>().apply {
    add(coreModule)
    add(asyncModule)
    add(networkModule)
    if (isDebug) {
        add(timeTravelModule)
    }
    add(
        module {
            factory<CocktailsComponent> {
                    (
                        platformContext: PlatformContext,
                        componentContext: ComponentContext
                    ) ->
                cocktailsComponent(
                    platformContext = platformContext,
                    componentContext = componentContext
                )
            }
        }
    )
}