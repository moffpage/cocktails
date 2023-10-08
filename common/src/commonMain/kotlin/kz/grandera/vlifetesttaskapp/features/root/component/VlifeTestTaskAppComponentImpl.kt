package kz.grandera.vlifetesttaskapp.features.root.component

import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.navigate

import kz.grandera.vlifetesttaskapp.features.root.component.VlifeTestTaskAppComponent.Child
import kz.grandera.vlifetesttaskapp.features.cocktails.root.component.cocktailsComponentFactory
import kz.grandera.vlifetesttaskapp.time_travel_client.component.timeTravelComponentFactory

internal class VlifeTestTaskAppComponentImpl(componentContext: ComponentContext) :
    VlifeTestTaskAppComponent,
    ComponentContext by componentContext
{
    private val navigation = StackNavigation<Configuration>()
    private val childStack = childStack(
        source = navigation,
        childFactory = { configuration, componentContext ->
            when (configuration) {
                is Configuration.Cocktails -> Child.Cocktails(
                    component = cocktailsComponentFactory(
                        componentContext = componentContext
                    )
                )
                is Configuration.TimeTravelLookOver -> Child.TimeTravelLookOver(
                    component = timeTravelComponentFactory(
                        componentContext = componentContext,
                        onNavigateBack = { navigation.pop() }
                    )
                )
            }
        },
        handleBackButton = true,
        initialConfiguration = Configuration.Cocktails
    )

    override val model: Value<ChildStack<*, Child>> = childStack

    override fun showTimeTravelLookOver() {
        navigation.navigate { configurations ->
            (configurations + Configuration.TimeTravelLookOver).distinct()
        }
    }

    private sealed interface Configuration : Parcelable {
        @Parcelize
        data object Cocktails : Configuration

        @Parcelize
        data object TimeTravelLookOver : Configuration
    }
}