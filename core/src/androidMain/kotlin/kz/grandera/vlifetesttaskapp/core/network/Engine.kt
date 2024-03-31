package kz.grandera.vlifetesttaskapp.core.network

import java.util.concurrent.TimeUnit

import kotlin.time.Duration

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

internal actual fun httpEngine(
    readTimeout: Duration,
    connectTimeout: Duration,
): HttpClientEngine = OkHttp.create {
    config {
        readTimeout(readTimeout.inWholeSeconds, TimeUnit.SECONDS)
        connectTimeout(connectTimeout.inWholeSeconds, TimeUnit.SECONDS)
    }
}