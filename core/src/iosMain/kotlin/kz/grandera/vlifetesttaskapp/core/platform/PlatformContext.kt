package kz.grandera.vlifetesttaskapp.core.platform

import dev.icerock.moko.resources.StringResource
import kz.grandera.vlifetesttaskapp.core.files.Uri

public actual abstract class PlatformContext

public actual fun PlatformContext.shareTextFile(
    fileUri: Uri,
    title: StringResource,
    subject: StringResource
) {}