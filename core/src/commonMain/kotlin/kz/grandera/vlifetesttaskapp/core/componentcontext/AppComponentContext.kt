package kz.grandera.vlifetesttaskapp.core.componentcontext

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope

import com.arkivanov.decompose.GenericComponentContext
import io.github.aakira.napier.Napier

import kz.grandera.vlifetesttaskapp.core.scope.ParentScopeIdOwner
import kz.grandera.vlifetesttaskapp.core.coroutines.taskhandler.TaskHandlerOwner

public interface AppComponentContext :
    GenericComponentContext<AppComponentContext>,
    ParentScopeIdOwner,
    TaskHandlerOwner,
    CoroutineScope
{
    public fun handleException(
        context: CoroutineContext,
        throwable: Throwable
    ) {
        Napier.e(throwable, message = { throwable.toString() })
    }
}