package kz.grandera.vlifetesttaskapp.core.platform

import dev.icerock.moko.resources.StringResource

import kz.grandera.vlifetesttaskapp.core.files.Uri

public expect abstract class PlatformContext

public expect fun PlatformContext.shareTextFile(
    fileUri: Uri,
    title: StringResource,
    subject: StringResource,
)