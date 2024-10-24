package kz.grandera.vlifetesttaskapp.core.extensions

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.MutableStateFlow

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.ChildStack

import kz.grandera.vlifetesttaskapp.core.event.EventsProducer
import kz.grandera.vlifetesttaskapp.core.event.back.BackEvent

private class ValueStateFlow<out T : Any>(private val v: Value<T>) : StateFlow<T> {
    override val value: T get() = v.value
    override val replayCache: List<T> get() = listOf(v.value)

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        val flow = MutableStateFlow(value = v.value)
        val observer: (T) -> Unit = { flow.value = it }
        val cancellation = v.subscribe(observer = observer)

        try {
            flow.collect(collector = collector)
        } finally {
            cancellation.cancel()
        }
    }
}

public fun <T : Any> Value<T>.asStateFlow(): StateFlow<T> = ValueStateFlow(this)

@OptIn(ExperimentalCoroutinesApi::class)
public fun <Event : Any> Value<ChildStack<*, *>>.childrenEvents(): Flow<Event> =
    this.asStateFlow()
        .map { stack -> stack.active.instance }
        .filterIsInstance<EventsProducer<Event>>()
        .flatMapLatest { component -> component.event }

public fun Value<ChildStack<*, *>>.childrenBackEvents(): Flow<BackEvent> = childrenEvents()