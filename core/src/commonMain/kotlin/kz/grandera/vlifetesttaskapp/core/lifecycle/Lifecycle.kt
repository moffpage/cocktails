package kz.grandera.vlifetesttaskapp.core.lifecycle

import kotlin.coroutines.CoroutineContext

import kotlinx.coroutines.cancel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.CoroutineScope

import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.lifecycle.LifecycleOwner

import kz.grandera.vlifetesttaskapp.core.async.mainDispatcher

public fun LifecycleOwner.coroutineScope(
    context: CoroutineContext = SupervisorJob() + mainDispatcher
): CoroutineScope {
    val scope = CoroutineScope(context = context)

    lifecycle.doOnDestroy {
        scope.cancel()
    }

    return scope
}