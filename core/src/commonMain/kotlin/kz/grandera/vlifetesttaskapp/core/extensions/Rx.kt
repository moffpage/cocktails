package kz.grandera.vlifetesttaskapp.core.extensions

import com.arkivanov.mvikotlin.core.store.Store

import com.arkivanov.decompose.Cancellation
import com.arkivanov.mvikotlin.core.rx.observer
import com.arkivanov.decompose.value.Value

public val <Wrapped : Any> Store<*, Wrapped, *>.states: Value<Wrapped>
    get() = object : Value<Wrapped>() {
        override val value: Wrapped get() = state

        override fun subscribe(observer: (Wrapped) -> Unit): Cancellation {
            val disposable = states(observer = observer(onNext = observer))
            return Cancellation { disposable.dispose() }
        }
    }