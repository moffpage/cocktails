package kz.grandera.vlifetesttaskapp.core.async

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal val mainDispatcher: CoroutineDispatcher = Dispatchers.Main.immediate
internal val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
