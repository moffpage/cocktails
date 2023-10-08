package kz.grandera.vlifetesttaskapp.core.module

import kotlin.coroutines.CoroutineContext

import io.ktor.client.HttpClient

import org.koin.dsl.module
import org.koin.core.module.Module
import org.koin.core.qualifier.named

import kz.grandera.vlifetesttaskapp.core.async.ioDispatcher
import kz.grandera.vlifetesttaskapp.core.async.mainDispatcher
import kz.grandera.vlifetesttaskapp.core.files.FileManager
import kz.grandera.vlifetesttaskapp.core.network.httpClient
import kz.grandera.vlifetesttaskapp.core.build_config.isDebug

public val asyncModule: Module = module {
    single<CoroutineContext>(qualifier = named(name = "IO")) { ioDispatcher }
    single<CoroutineContext>(qualifier = named(name = "Main")) { mainDispatcher }
}

public val filesModule: Module = module {
    single { FileManager(context = get()) }
}

public val httpClientModule: Module = module {
    single<HttpClient> { httpClient(enableLogging = isDebug) }
}