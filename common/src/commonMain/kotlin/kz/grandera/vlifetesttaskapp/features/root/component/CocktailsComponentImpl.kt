package kz.grandera.vlifetesttaskapp.features.root.component

import kotlinx.serialization.Serializable

import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation

import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent.Child
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponentImpl.Configuration
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponentImpl
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponentImpl

internal class CocktailsComponentImpl(componentContext: ComponentContext) :
    CocktailsComponent,
    ComponentContext by componentContext,
    BackHandlerOwner
{
    private val navigation = StackNavigation<Configuration>()
    private val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        childFactory = { configuration, componentContext ->
            child(
                configuration = configuration,
                componentContext = componentContext
            )
        },
        handleBackButton = true,
        initialConfiguration = Configuration.List
    )

    override val model: Value<ChildStack<*, Child>> = childStack

    override fun onBackInvoked() {
        navigation.pop()
    }

    internal fun listComponent(componentContext: ComponentContext): CocktailsListComponent =
        CocktailsListComponentImpl(
            componentContext = componentContext,
            onShowCocktail = { id ->
                navigation.pushNew(
                    configuration = Configuration.Details(
                        id = id
                    )
                )
            }
        )

    internal fun detailsComponent(id: Long, componentContext: ComponentContext): CocktailDetailsComponent =
        CocktailDetailsComponentImpl(
            id = id,
            componentContext = componentContext,
            onNavigateBack = { navigation.pop() }
        )

    @Serializable
    internal sealed interface Configuration {
        @Serializable
        data object List : Configuration

        @Serializable
        data class Details(val id: Long) : Configuration
    }
}

private fun CocktailsComponentImpl.child(
    configuration: Configuration,
    componentContext: ComponentContext
): Child = when (configuration) {
        is Configuration.List -> Child.CocktailsList(
            component = listComponent(
                componentContext = componentContext
            )
        )
        is Configuration.Details -> Child.CocktailDetails(
            component = detailsComponent(
                id = configuration.id,
                componentContext = componentContext
            )
        )
    }