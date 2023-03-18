package kz.grandera.vlifetesttaskapp.utils

import platform.UIKit.UIPasteboard

import dev.icerock.moko.resources.StringResource

public actual abstract class PlatformContext
public actual fun PlatformContext.shareText(
    data: String,
    title: StringResource,
    subject: StringResource
) {}

public actual fun PlatformContext.getTextFromClipboard(): String? = UIPasteboard.generalPasteboard.string
