package kz.grandera.vlifetesttaskapp.core.network

import kotlin.time.toDuration
import kotlin.time.DurationUnit

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

import io.github.aakira.napier.Napier

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

@OptIn(ExperimentalSerializationApi::class)
private val json = Json {
    isLenient = true
    explicitNulls = false
    encodeDefaults = true
    ignoreUnknownKeys = true
}

private const val TIMEOUT_SECONDS = 60.0
private val timeout = TIMEOUT_SECONDS.toDuration(
    unit = DurationUnit.SECONDS
)

private const val BASE_URL = "https://thecocktaildb.com/api/json/v1/1/"

internal fun httpClient(enableLogging: Boolean): HttpClient = HttpClient(
    engine = httpEngine(
        readTimeout = timeout,
        connectTimeout = timeout,
    )
) {
    expectSuccess = true

    if (enableLogging) {
        install(plugin = Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "HTTP", message = message)
                }
            }
        }
    }

    install(plugin = DefaultRequest) {
        url(urlString = BASE_URL)
    }

    install(plugin = ContentNegotiation.Plugin) {
        json(json = json)
    }
}