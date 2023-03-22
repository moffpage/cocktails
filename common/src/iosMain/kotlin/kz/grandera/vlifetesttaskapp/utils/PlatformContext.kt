package kz.grandera.vlifetesttaskapp.utils

import dev.icerock.moko.resources.StringResource

public actual abstract class PlatformContext

public actual fun PlatformContext.shareTextFile(
    fileUri: Uri,
    title: StringResource,
    subject: StringResource
) {}