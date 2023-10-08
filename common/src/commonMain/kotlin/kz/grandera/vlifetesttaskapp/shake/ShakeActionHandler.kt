package kz.grandera.vlifetesttaskapp.shake

import kz.grandera.vlifetesttaskapp.core.platform.PlatformContext

public expect class ShakeActionHandler public constructor(
    context: PlatformContext,
    detector: ShakeDetector
)