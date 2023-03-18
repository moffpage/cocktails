package kz.grandera.vlifetesttaskapp.utils

import android.util.Base64

internal actual fun String.decodeBase64(): ByteArray = Base64.decode(this, Base64.DEFAULT)
internal actual fun ByteArray.encodeBase64(): String = Base64.encodeToString(this, Base64.DEFAULT)