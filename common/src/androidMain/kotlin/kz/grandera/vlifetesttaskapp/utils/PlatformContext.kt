package kz.grandera.vlifetesttaskapp.utils

import android.content.Intent
import android.content.Context

import dev.icerock.moko.resources.StringResource

public actual fun PlatformContext.shareTextFile(
    fileUri: Uri,
    title: StringResource,
    subject: StringResource
) {
    val intent = Intent(Intent.ACTION_SEND)
        .apply {
            type = "text/*"
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            putExtra(Intent.EXTRA_STREAM, fileUri)
            putExtra(Intent.EXTRA_SUBJECT, subject.resourceId)
        }

    val chooser = Intent.createChooser(
        intent,
        getText(title.resourceId)
    )

    startActivity(
        chooser.withGrantedReadPermission(
            forUri = fileUri,
            context = this
        )
    )
}

public actual typealias PlatformContext = Context

