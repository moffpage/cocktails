package kz.grandera.vlifetesttaskapp.features.root.component

import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.ChildStack

import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent

public interface CocktailsComponent : BackHandlerOwner {
    public sealed interface Child {
        public data class CocktailsList(
            public val component: CocktailsListComponent
        ) : Child
        public data class CocktailDetails(
            public val component: CocktailDetailsComponent
        ) : Child
    }

    public val model: Value<ChildStack<*, Child>>

    public fun onBackInvoked()
}