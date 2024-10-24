package kz.grandera.vlifetesttaskapp.core.event.back

import kz.grandera.vlifetesttaskapp.core.event.DefaultEventsProducerDelegate

private class DefaultBackEventsProducerDelegate : DefaultEventsProducerDelegate<BackEvent>()

public fun backEventsProducerDelegate(): BackEventsProducerDelegate = DefaultBackEventsProducerDelegate()