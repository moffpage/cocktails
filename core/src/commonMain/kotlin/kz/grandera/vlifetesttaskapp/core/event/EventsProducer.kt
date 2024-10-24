package kz.grandera.vlifetesttaskapp.core.event

import kotlinx.coroutines.flow.Flow

public interface EventsProducer<out Event: Any> {
    public val event: Flow<Event>
}

public interface EventsProducerDelegate<Event: Any> : EventsProducer<Event> {
    public fun dispatch(event: Event)
}