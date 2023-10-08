package kz.grandera.vlifetesttaskapp.time_travel_client.component

import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.timetravel.export.TimeTravelExportSerializer
import com.arkivanov.mvikotlin.timetravel.export.TimeTravelExportSerializer.Result
import com.arkivanov.mvikotlin.timetravel.controller.timeTravelController

import io.ktor.util.encodeBase64
import io.ktor.util.decodeBase64Bytes

import org.koin.core.component.inject
import org.koin.core.component.KoinComponent

import dev.icerock.moko.resources.StringResource

import kz.grandera.vlifetesttaskapp.core.date.currentTimeFormatted
import kz.grandera.vlifetesttaskapp.core.files.Uri
import kz.grandera.vlifetesttaskapp.core.files.FileManager
import kz.grandera.vlifetesttaskapp.core.lifecycle.coroutineScope
import kz.grandera.vlifetesttaskapp.core.platform.PlatformContext
import kz.grandera.vlifetesttaskapp.core.platform.shareTextFile
import kz.grandera.vlifetesttaskapp.time_travel_client.resources.Strings
import kz.grandera.vlifetesttaskapp.time_travel_client.component.TimeTravelClientComponent.Event

internal class TimeTravelClientComponentImpl(
    componentContext: ComponentContext,
    private val onNavigateBack: () -> Unit
) : TimeTravelClientComponent,
    KoinComponent,
    ComponentContext by componentContext
{
    private val serializer by inject<TimeTravelExportSerializer>()
    private val fileManager by inject<FileManager>()
    private val platformContext by inject<PlatformContext>()

    private val scope = coroutineScope()

    private val _events: MutableSharedFlow<Event> = MutableSharedFlow(replay = 0)
    override val events: Flow<Event> = _events

    override fun navigateBack() {
        onNavigateBack()
    }

    override fun exportEvents() {
        val exportedData = timeTravelController.export()
        when (val serializationResult = serializer.serialize(export = exportedData)) {
            is Result.Error -> { showError(errorText = Strings.timeTravelSerializeError) }
            is Result.Success -> {
                val currentTimeString = currentTimeFormatted()
                val encodedData = serializationResult.data.encodeBase64()
                val fileName = "time-travel_export_$currentTimeString.txt"
                val fileUri = fileManager.createWriteText(
                    text = encodedData,
                    fileName = fileName,
                )
                platformContext.shareTextFile(
                    fileUri = fileUri,
                    title = Strings.timeTravelExportChooserTitle,
                    subject = Strings.timeTravelExportSubject
                )
            }
        }
    }

    override fun importEvents(uri: Uri) {
        val importedData = fileManager.readText(fileUri = uri)
        if (importedData.isBlank()) {
            showError(errorText = Strings.timeTravelClipboardEmpty)
        } else {
            val decodedData = importedData.decodeBase64Bytes()
            when (val deserializationResult = serializer.deserialize(data = decodedData)) {
                is Result.Error -> {
                    showError(
                        errorText = Strings.timeTravelDeserializeError
                    )
                }
                is Result.Success -> {
                    timeTravelController.import(
                        export = deserializationResult.data
                    )
                }
            }
        }
    }

    private fun showError(errorText: StringResource) {
        scope.launch {
            _events.emit(
                value = Event.ErrorOccurred(
                    message = errorText
                )
            )
        }
    }
}