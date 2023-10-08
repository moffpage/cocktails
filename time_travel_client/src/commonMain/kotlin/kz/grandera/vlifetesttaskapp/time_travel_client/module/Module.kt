package kz.grandera.vlifetesttaskapp.time_travel_client.module

import org.koin.dsl.module
import org.koin.core.module.Module

import com.arkivanov.mvikotlin.timetravel.export.TimeTravelExportSerializer

import kz.grandera.vlifetesttaskapp.time_travel_client.timeTravelExportSerializer

public val timeTravelModule: Module = module {
    single<TimeTravelExportSerializer> { timeTravelExportSerializer }
}