package kz.grandera.vlifetesttaskapp.core.componentcontext

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.IO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler

import org.koin.core.scope.ScopeID

import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.essenty.statekeeper.StateKeeperOwner
import com.arkivanov.essenty.instancekeeper.InstanceKeeperOwner
import com.arkivanov.decompose.ComponentContextFactory
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.GenericComponentContext

import kz.grandera.vlifetesttaskapp.core.coroutines.excludeExceptions
import kz.grandera.vlifetesttaskapp.core.coroutines.ExcludeExceptions
import kz.grandera.vlifetesttaskapp.core.coroutines.taskhandler.TaskHandler
import kz.grandera.vlifetesttaskapp.core.coroutines.taskhandler.TaskHandlerContext

public class DefaultAppComponentContext(
    componentContext: GenericComponentContext<*>,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    override val taskHandler: TaskHandler = TaskHandler(),
    override val parentScopeId: ScopeID?,
) : AppComponentContext,
    LifecycleOwner by componentContext,
    StateKeeperOwner by componentContext,
    InstanceKeeperOwner by componentContext,
    BackHandlerOwner by componentContext
{
    private val job by lazy { SupervisorJob() }
    private val taskHandlerContext by lazy { TaskHandlerContext() }
    private val exceptionHandler by lazy {
        CoroutineExceptionHandler { context, throwable ->
            if (throwable is CancellationException) return@CoroutineExceptionHandler

            if (
                throwable.isNotExcludedFromHandling(
                    excludeExceptions = context.excludeExceptions
                )
            ) {
                handleException(
                    context = context,
                    throwable = throwable
                )
            }
        }
    }

    init {
        lifecycle.doOnDestroy {
            taskHandler.cancelAll()
            job.cancelChildren()
        }
    }

    override val coroutineContext: CoroutineContext =
        job + dispatcher + taskHandlerContext + exceptionHandler

    override val componentContextFactory: ComponentContextFactory<AppComponentContext> =
        ComponentContextFactory<AppComponentContext> { lifecycle, stateKeeper, instanceKeeper, backHandler ->
            DefaultAppComponentContext(
                parentScopeId = parentScopeId,
                componentContext = DefaultComponentContext(
                    lifecycle = lifecycle,
                    backHandler = backHandler,
                    stateKeeper = stateKeeper,
                    instanceKeeper = instanceKeeper
                )
            )
        }
}

private fun Throwable.isNotExcludedFromHandling(
    excludeExceptions: ExcludeExceptions?
): Boolean = excludeExceptions == null ||
        excludeExceptions.exceptions.isNotEmpty() && !excludeExceptions.contains(this)

public fun wrapComponentContext(
    context: GenericComponentContext<*>,
    parentScopeId: ScopeID?,
): AppComponentContext =
    DefaultAppComponentContext(
        parentScopeId = parentScopeId,
        componentContext = context
    )