package kz.grandera.vlifetesttaskapp.core.componentcontext

import org.koin.core.scope.ScopeID

import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.statekeeper.StateKeeperOwner
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.essenty.instancekeeper.InstanceKeeperOwner
import com.arkivanov.decompose.ComponentContextFactory
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.GenericComponentContext

public class DefaultAppComponentContext(
    context: GenericComponentContext<*>,
    override val parentScopeId: ScopeID?,
) : AppComponentContext,
    LifecycleOwner by context,
    StateKeeperOwner by context,
    InstanceKeeperOwner by context,
    BackHandlerOwner by context {

    override val componentContextFactory: ComponentContextFactory<AppComponentContext> =
        ComponentContextFactory<AppComponentContext> { lifecycle, stateKeeper, instanceKeeper, backHandler ->
            DefaultAppComponentContext(
                context = DefaultComponentContext(
                    lifecycle = lifecycle,
                    stateKeeper = stateKeeper,
                    instanceKeeper = instanceKeeper,
                    backHandler = backHandler,
                ),
                parentScopeId = parentScopeId,
            )
        }
}

public fun wrapComponentContext(
    context: GenericComponentContext<*>,
    parentScopeId: ScopeID?,
): AppComponentContext =
    DefaultAppComponentContext(
        context = context,
        parentScopeId = parentScopeId,
    )