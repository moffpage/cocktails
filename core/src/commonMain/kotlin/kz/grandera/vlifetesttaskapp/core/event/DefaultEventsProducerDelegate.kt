package kz.grandera.vlifetesttaskapp.core.event

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.channels.Channel

public class DefaultEventsProducerDelegate<Event : Any> : EventsProducerDelegate<Event> {
    private val _event = Channel<Event>(Channel.UNLIMITED)

    override val event: Flow<Event> = _event.receiveAsFlow()

    public override fun dispatch(event: Event) {
        _event.trySend(event)
    }
}