package kz.grandera.vlifetesttaskapp.core.coroutines.extensions

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

import kz.grandera.vlifetesttaskapp.core.coroutines.LaunchStrategy
import kz.grandera.vlifetesttaskapp.core.coroutines.KeepPreviousStrategy
import kz.grandera.vlifetesttaskapp.core.coroutines.KillPreviousStrategy
import kz.grandera.vlifetesttaskapp.core.coroutines.taskhandler.TaskHandlerContext

public fun CoroutineScope.safeLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val launchStrategy = context[LaunchStrategy]
    if (launchStrategy != null) {
        val coroutineName = CoroutineName(launchStrategy.id)
        return handleStrategyLaunch(coroutineContext + coroutineName + context, start, block)
    }
    return launch(context, start, block)
}

private fun CoroutineScope.handleStrategyLaunch(
    context: CoroutineContext,
    start: CoroutineStart,
    block: suspend CoroutineScope.() -> Unit
): Job {
    requireNotNull(context[TaskHandlerContext]) {
        "TaskHandler must be provided in the context"
    }
    return when (requireNotNull(context[LaunchStrategy])) {
        is KeepPreviousStrategy -> handleKeepPreviousLaunch(context, start, block)
        is KillPreviousStrategy -> handleKillPreviousLaunch(context, start, block)
    }
}

private fun CoroutineScope.handleKeepPreviousLaunch(
    context: CoroutineContext,
    start: CoroutineStart,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val taskHandler = requireNotNull(context[TaskHandlerContext])
    val id = requireNotNull(context[LaunchStrategy]).id
    val previousJob = taskHandler.tasks[id]
    if (previousJob?.isActive == true) {
        return previousJob
    }
    return launch(context, start, block).also {
        taskHandler.tasks[id] = it
    }
}

private fun CoroutineScope.handleKillPreviousLaunch(
    context: CoroutineContext,
    start: CoroutineStart,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val taskHandler = requireNotNull(context[TaskHandlerContext])
    val id = requireNotNull(context[LaunchStrategy]).id
    val previousJob = taskHandler.tasks[id]
    if (previousJob?.isActive == true) {
        previousJob.cancel()
    }
    return launch(context, start, block).also {
        taskHandler.tasks[id] = it
    }
}