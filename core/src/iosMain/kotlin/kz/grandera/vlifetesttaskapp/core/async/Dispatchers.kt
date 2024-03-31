package kz.grandera.vlifetesttaskapp.core.async

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher

internal actual val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
internal actual val ioDispatcher: CoroutineDispatcher = Dispatchers.Main