package kz.grandera.vlifetesttaskapp.core.network

import kotlin.time.Duration

import io.ktor.client.engine.HttpClientEngine

internal expect fun httpEngine(
    readTimeout: Duration,
    connectTimeout: Duration,
): HttpClientEngine