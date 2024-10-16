package kz.grandera.vlifetesttaskapp.core.coroutines

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineName

import kz.grandera.vlifetesttaskapp.core.coroutines.taskhandler.TaskHandlerContext

public val CoroutineContext.name: String?
    get() = this[CoroutineName]?.name

public val CoroutineContext.taskHandler: TaskHandlerContext?
    get() = get(TaskHandlerContext)

public val CoroutineContext.excludeExceptions: ExcludeExceptions?
    get() = get(ExcludeExceptions)