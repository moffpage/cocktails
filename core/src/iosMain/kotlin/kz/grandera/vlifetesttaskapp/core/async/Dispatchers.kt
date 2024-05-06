package kz.grandera.vlifetesttaskapp.core.async

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.IO

internal actual val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
internal actual val ioDispatcher: CoroutineDispatcher = Dispatchers.IO