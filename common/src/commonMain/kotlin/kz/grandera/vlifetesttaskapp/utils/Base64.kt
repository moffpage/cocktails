package kz.grandera.vlifetesttaskapp.utils

internal expect fun String.decodeBase64(): ByteArray
internal expect fun ByteArray.encodeBase64(): String