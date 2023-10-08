package kz.grandera.vlifetesttaskapp.core.build_config

import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
public actual val isDebug: Boolean = Platform.isDebugBinary
public actual val timeTravelPort: Int = 6969