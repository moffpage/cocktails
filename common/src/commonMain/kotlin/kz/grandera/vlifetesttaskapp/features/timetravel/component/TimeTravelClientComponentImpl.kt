package kz.grandera.vlifetesttaskapp.features.timetravel.component

import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.timetravel.export.TimeTravelExportSerializer
import com.arkivanov.mvikotlin.timetravel.export.TimeTravelExportSerializer.Result
import com.arkivanov.mvikotlin.timetravel.controller.timeTravelController

import io.ktor.util.encodeBase64
import io.ktor.util.decodeBase64Bytes

import dev.icerock.moko.resources.StringResource

import kz.grandera.vlifetesttaskapp.utils.shareText
import kz.grandera.vlifetesttaskapp.utils.coroutineScope
import kz.grandera.vlifetesttaskapp.utils.PlatformContext
import kz.grandera.vlifetesttaskapp.utils.getTextFromClipboard
import kz.grandera.vlifetesttaskapp.features.timetravel.component.TimeTravelClientComponent.Event
import kz.grandera.vlifetesttaskapp.resources.Strings

internal class TimeTravelClientComponentImpl(
    componentContext: ComponentContext,
    private val platformContext: PlatformContext,
    private val serializer: TimeTravelExportSerializer,
    private val onNavigateBack: () -> Unit
) : TimeTravelClientComponent,
    ComponentContext by componentContext
{
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
                val encodedData = serializationResult.data.encodeBase64()
                platformContext.shareText(
                    data = encodedData,
                    title = Strings.timeTravelExportChooserTitle,
                    subject = Strings.timeTravelExportSubject
                )
            }
        }
    }

    override fun importEvents() {
        val importedData = platformContext.getTextFromClipboard()
        if (importedData == null) {
            showError(errorText = Strings.timeTravelClipboardEmpty)
        } else {
            val decodedData = importedData.decodeBase64Bytes()
            when (val deserializationResult = serializer.deserialize(data = decodedData)) {
                is Result.Error -> { showError(errorText = Strings.timeTravelDeserializeError) }
                is Result.Success -> {
                    val dataToImport = deserializationResult.data
                    timeTravelController.import(export = dataToImport)
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