package kz.grandera.vlifetesttaskapp.async

import kotlinx.coroutines.CoroutineDispatcher

internal expect val mainDispatcher: CoroutineDispatcher
internal expect val ioDispatcher: CoroutineDispatcher