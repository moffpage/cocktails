package kz.grandera.vlifetesttaskapp.features.root.component

import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.ChildStack

import kz.grandera.vlifetesttaskapp.component.Component
import kz.grandera.vlifetesttaskapp.core.componentcontext.AppComponentContext

public interface CocktailsComponent : BackHandlerOwner {
    public fun interface Factory {
        public fun create(componentContext: AppComponentContext) : CocktailsComponent
    }

    public val model: Value<ChildStack<*, Component>>

    public fun onBackInvoked()
}