package kz.grandera.vlifetesttaskapp.core.coroutines

import kotlin.coroutines.CoroutineContext

public sealed interface LaunchStrategy : CoroutineContext.Element {

    public companion object Key : CoroutineContext.Key<LaunchStrategy> {

        public fun keepPrevious(id: String): LaunchStrategy = KeepPreviousStrategy(id)

        public fun killPrevious(id: String): LaunchStrategy = KillPreviousStrategy(id)
    }

    override val key: CoroutineContext.Key<*> get() = Key

    public val id: String
}

internal class KeepPreviousStrategy(override val id: String) : LaunchStrategy

internal class KillPreviousStrategy(override val id: String) : LaunchStrategy