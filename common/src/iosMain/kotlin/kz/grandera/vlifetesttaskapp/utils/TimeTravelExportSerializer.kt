package kz.grandera.vlifetesttaskapp.utils

import com.arkivanov.mvikotlin.timetravel.export.TimeTravelExport
import com.arkivanov.mvikotlin.timetravel.export.TimeTravelExportSerializer
import com.arkivanov.mvikotlin.timetravel.export.TimeTravelExportSerializer.Result

internal actual val timeTravelExportSerializer: TimeTravelExportSerializer =
    object : TimeTravelExportSerializer {
        override fun serialize(export: TimeTravelExport): Result<ByteArray> =
            TODO("Not yet implemented")

        override fun deserialize(data: ByteArray): Result<TimeTravelExport> =
            TODO("Not yet implemented")
    }