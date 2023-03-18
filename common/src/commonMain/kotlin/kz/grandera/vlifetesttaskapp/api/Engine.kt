package kz.grandera.vlifetesttaskapp.api

import kotlin.time.Duration

import io.ktor.client.engine.HttpClientEngine

internal expect fun httpEngine(
    readTimeout: Duration,
    connectTimeout: Duration,
): HttpClientEngine