package kz.grandera.vlifetesttaskapp.features.cocktails.root.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.ChildStack

import kz.grandera.vlifetesttaskapp.features.cocktails.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.features.cocktails.details.component.CocktailDetailsComponent

public interface CocktailsComponent {
    public sealed interface Child {
        public data class CocktailsList(
            public val component: CocktailsListComponent
        ) : Child
        public data class CocktailDetails(
            public val component: CocktailDetailsComponent
        ) : Child
    }

    public val model: Value<ChildStack<*, Child>>
}