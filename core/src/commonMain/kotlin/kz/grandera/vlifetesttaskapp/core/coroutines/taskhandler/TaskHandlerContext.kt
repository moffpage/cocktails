package kz.grandera.vlifetesttaskapp.core.coroutines.taskhandler

import kotlin.coroutines.CoroutineContext

import kotlinx.coroutines.Job

public class TaskHandlerContext : CoroutineContext.Element {

    public companion object Key : CoroutineContext.Key<TaskHandlerContext>

    override val key: CoroutineContext.Key<*> = Key

    internal val tasks = HashMap<String, Job>()

    public fun cancel(id: String) {
        tasks[id]?.cancel()
        tasks.remove(id)
    }
}