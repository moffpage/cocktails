package kz.grandera.vlifetesttaskapp.utils

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTimeTz

internal fun currentTimeFormatted(): String {
    val now = DateTimeTz.nowLocal()
    val dateTimeFormatter = DateFormat(pattern = "MM-dd_HH:mm:ss")
    return dateTimeFormatter.format(dd = now)
}