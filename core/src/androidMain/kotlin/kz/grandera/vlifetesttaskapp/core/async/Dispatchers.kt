package kz.grandera.vlifetesttaskapp.core.async

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher

internal actual val mainDispatcher: CoroutineDispatcher = Dispatchers.Main.immediate
internal actual val ioDispatcher: CoroutineDispatcher = Dispatchers.IO