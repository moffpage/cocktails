package kz.grandera.vlifetesttaskapp.core.coroutines.extensions

import kotlinx.coroutines.Job
import kotlinx.coroutines.CancellationException

public fun Job.invokeOnFailure(action: (throwable: Throwable) -> Unit) {
    invokeOnCompletion { throwable ->
        if (throwable == null || throwable is CancellationException) {
            return@invokeOnCompletion
        }
        action(throwable)
    }
}