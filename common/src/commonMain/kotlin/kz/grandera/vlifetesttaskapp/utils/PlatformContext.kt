package kz.grandera.vlifetesttaskapp.utils

import dev.icerock.moko.resources.StringResource

public expect abstract class PlatformContext

public expect fun PlatformContext.shareText(
    data: String,
    title: StringResource,
    subject: StringResource,
)
public expect fun PlatformContext.getTextFromClipboard(): String?