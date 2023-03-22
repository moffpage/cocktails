package kz.grandera.vlifetesttaskapp.utils

import android.net.Uri
import android.content.Intent
import android.content.Context
import android.content.pm.PackageManager

internal fun Intent.withGrantedReadPermission(forUri: Uri, context: Context): Intent {
    addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)

    val flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION or
            Intent.FLAG_GRANT_READ_URI_PERMISSION

    val resolveInfoList = context.packageManager.queryIntentActivities(
        this,
        PackageManager.MATCH_DEFAULT_ONLY
    )

    for (resolveInfo in resolveInfoList) {
        context.grantUriPermission(
            resolveInfo.activityInfo.packageName,
            forUri,
            flags
        )
    }

    return this
}