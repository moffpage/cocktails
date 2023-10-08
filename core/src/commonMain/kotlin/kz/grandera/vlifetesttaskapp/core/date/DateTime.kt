package kz.grandera.vlifetesttaskapp.core.date

import korlibs.time.DateFormat
import korlibs.time.DateTimeTz

public fun currentTimeFormatted(): String {
    val now = DateTimeTz.nowLocal()
    val dateTimeFormatter = DateFormat(pattern = "MM-dd_HH:mm:ss")
    return dateTimeFormatter.format(dd = now)
}