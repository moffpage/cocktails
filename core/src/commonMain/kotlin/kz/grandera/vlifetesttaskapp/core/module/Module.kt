package kz.grandera.vlifetesttaskapp.core.module

import kotlin.coroutines.CoroutineContext

import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

import org.koin.dsl.module
import org.koin.core.module.Module
import org.koin.core.qualifier.named

import kz.grandera.vlifetesttaskapp.core.network.httpClient
import kz.grandera.vlifetesttaskapp.core.build_config.isDebug

public val asyncModule: Module = module {
    single<CoroutineContext>(qualifier = named(name = "IO")) { Dispatchers.IO }
    single<CoroutineContext>(qualifier = named(name = "Main")) { Dispatchers.Main }
}

public val httpClientModule: Module = module {
    single<HttpClient> { httpClient(enableLogging = isDebug) }
}