package kz.grandera.vlifetesttaskapp.core.event

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

public abstract class DefaultEventsProducerDelegate<Event : Any> : EventsProducerDelegate<Event> {
    private val _event = MutableSharedFlow<Event>(extraBufferCapacity = 1)

    override val event: Flow<Event> = _event

    public override fun dispatch(event: Event) {
        _event.tryEmit(event)
    }
}