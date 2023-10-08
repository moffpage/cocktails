package kz.grandera.vlifetesttaskapp.core.network

import kotlin.time.Duration

import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.engine.HttpClientEngine

public actual fun httpEngine(
    readTimeout: Duration,
    connectTimeout: Duration,
): HttpClientEngine = Darwin.create {
    configureSession {
        waitsForConnectivity = true
        timeoutIntervalForRequest = readTimeout.inWholeSeconds.toDouble()
    }
    configureRequest {
        setAllowsCellularAccess(true)
    }
}