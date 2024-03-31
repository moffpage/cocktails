package kz.grandera.vlifetesttaskapp.core.async

import kotlinx.coroutines.CoroutineDispatcher

internal expect val mainDispatcher: CoroutineDispatcher
internal expect val ioDispatcher: CoroutineDispatcher