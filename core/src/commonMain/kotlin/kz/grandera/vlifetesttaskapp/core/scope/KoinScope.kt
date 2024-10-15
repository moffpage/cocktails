package kz.grandera.vlifetesttaskapp.core.scope

import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.lifecycle.LifecycleOwner

import org.koin.mp.KoinPlatform
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeID
import org.koin.core.scope.ScopeCallback
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

public fun <T> T.koinScope(
    vararg modules: Module,
    scopeId: ScopeID,
    qualifier: Qualifier,
) : Scope where T : LifecycleOwner,
                T : ParentScopeIdOwner
{
    val scope = KoinPlatform.getKoin().createScope(
        scopeId = scopeId,
        qualifier = qualifier,
        source = null,
    )

    val modulesList = modules.toList()

    parentScopeId
        ?.let(scope::getScope)
        ?.let { parentScope ->
            scope.linkTo(parentScope)
        }

    KoinPlatform.getKoin().loadModules(modulesList)

    scope.registerCallback(
        object : ScopeCallback {
            override fun onScopeClose(scope: Scope) {
                KoinPlatform.getKoin().unloadModules(
                    modulesList
                )
            }
        }
    )

    lifecycle.doOnDestroy {
        scope.close()
    }

    return scope
}