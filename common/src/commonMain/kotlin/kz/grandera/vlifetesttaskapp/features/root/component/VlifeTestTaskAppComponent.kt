package kz.grandera.vlifetesttaskapp.features.root.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.ChildStack

import kz.grandera.vlifetesttaskapp.features.cocktails.root.component.CocktailsComponent
import kz.grandera.vlifetesttaskapp.time_travel_client.component.TimeTravelClientComponent

public interface VlifeTestTaskAppComponent {
    public val model: Value<ChildStack<*, Child>>

    public fun showTimeTravelLookOver()

    public sealed interface Child {
        public data class Cocktails(
            public val component: CocktailsComponent
        ) : Child
        public data class TimeTravelLookOver(
            public val component: TimeTravelClientComponent
        ) : Child
    }
}