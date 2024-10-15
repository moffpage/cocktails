package kz.grandera.vlifetesttaskapp.features.root.component

import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.ChildStack

import kz.grandera.vlifetesttaskapp.component.Component

public interface CocktailsComponent : BackHandlerOwner {

    public val model: Value<ChildStack<*, Component>>

    public fun onBackInvoked()
}