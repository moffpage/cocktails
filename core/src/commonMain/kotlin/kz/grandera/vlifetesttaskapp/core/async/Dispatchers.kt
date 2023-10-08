package kz.grandera.vlifetesttaskapp.core.async

import kotlinx.coroutines.CoroutineDispatcher

public expect val mainDispatcher: CoroutineDispatcher
public expect val ioDispatcher: CoroutineDispatcher