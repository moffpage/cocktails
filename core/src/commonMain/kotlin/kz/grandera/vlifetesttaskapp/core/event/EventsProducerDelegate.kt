package kz.grandera.vlifetesttaskapp.core.event

public interface EventsProducerDelegate<Event: Any> : EventsProducer<Event> {
    public fun dispatch(event: Event)
}