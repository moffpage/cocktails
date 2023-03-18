package kz.grandera.vlifetesttaskapp.utils

import android.content.Intent
import android.content.Context
import android.content.ClipboardManager

import dev.icerock.moko.resources.StringResource

public actual fun PlatformContext.shareText(
    data: String,
    title: StringResource,
    subject: StringResource
) {
    val intent = Intent(Intent.ACTION_SEND)
        .apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, subject.resourceId)
            putExtra(Intent.EXTRA_TEXT, data)
        }
    startActivity(
        Intent.createChooser(
            intent,
            getText(title.resourceId)
        )
    )
}

public actual fun PlatformContext.getTextFromClipboard(): String? =
    (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
        .primaryClip
        ?.takeIf { it.itemCount > 0 }
        ?.getItemAt(0)
        ?.text
        ?.toString()

public actual typealias PlatformContext = Context

