package kz.grandera.vlifetesttaskapp.utils

import dev.icerock.moko.resources.StringResource

public expect abstract class PlatformContext

public expect fun PlatformContext.shareTextFile(
    fileUri: Uri,
    title: StringResource,
    subject: StringResource,
)