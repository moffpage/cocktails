package kz.grandera.vlifetesttaskapp.core.async

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher

public actual val mainDispatcher: CoroutineDispatcher = Dispatchers.Main.immediate
public actual val ioDispatcher: CoroutineDispatcher = Dispatchers.IO