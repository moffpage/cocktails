package kz.grandera.vlifetesttaskapp.features.timetravel.component

import kotlinx.coroutines.flow.Flow

import dev.icerock.moko.resources.StringResource
import kz.grandera.vlifetesttaskapp.utils.Uri

public interface TimeTravelClientComponent {
    public sealed interface Event {
        public data class ErrorOccurred(
            public val message: StringResource
        ) : Event
    }

    public val events: Flow<Event>
    public fun exportEvents()
    public fun importEvents(uri: Uri)
    public fun navigateBack()
}