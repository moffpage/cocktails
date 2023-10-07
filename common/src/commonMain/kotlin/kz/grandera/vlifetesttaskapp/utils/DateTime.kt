package kz.grandera.vlifetesttaskapp.utils

import korlibs.time.DateFormat
import korlibs.time.DateTimeTz

internal fun currentTimeFormatted(): String {
    val now = DateTimeTz.nowLocal()
    val dateTimeFormatter = DateFormat(pattern = "MM-dd_HH:mm:ss")
    return dateTimeFormatter.format(dd = now)
}