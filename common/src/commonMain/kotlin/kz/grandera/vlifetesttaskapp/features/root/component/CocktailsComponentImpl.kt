package kz.grandera.vlifetesttaskapp.features.root.component

import kotlinx.serialization.Serializable

import org.koin.core.component.getScopeId
import org.koin.core.qualifier.qualifier

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation

import kz.grandera.vlifetesttaskapp.core.scope.koinScope
import kz.grandera.vlifetesttaskapp.core.componentcontext.AppComponentContext
import kz.grandera.vlifetesttaskapp.core.componentcontext.wrapComponentContext
import kz.grandera.vlifetesttaskapp.component.Component
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent

internal class CocktailsComponentImpl(componentContext: AppComponentContext) :
    CocktailsComponent,
    AppComponentContext by componentContext
{
    private val koinScope = koinScope(
        scopeId = getScopeId(),
        qualifier = qualifier<CocktailsComponent>(),
    )

    private val cocktailsListComponentFactory by koinScope.inject<CocktailsListComponent.Factory>()
    private val cocktailDetailsComponentFactory by koinScope.inject<CocktailDetailsComponent.Factory>()

    private val navigation = StackNavigation<Configuration>()
    private val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        childFactory = { configuration, componentContext ->
            child(
                context = componentContext,
                configuration = configuration
            )
        },
        handleBackButton = true,
        initialConfiguration = Configuration.List
    )

    override val model: Value<ChildStack<*, Component>> = childStack

    override fun onBackInvoked() {
        navigation.pop()
    }

    private fun child(
        context: AppComponentContext,
        configuration: Configuration,
    ): Component {
        val componentContext = wrapComponentContext(
            context = context,
            parentScopeId = koinScope.id
        )
        return when (configuration) {
            is Configuration.List -> cocktailsListComponentFactory.create(
                componentContext = componentContext,
                onShowCocktail = { cocktailId ->
                    navigation.pushNew(
                        Configuration.Details(
                            id = cocktailId
                        )
                    )
                }
            )

            is Configuration.Details -> cocktailDetailsComponentFactory.create(
                onBack = { navigation.pop() },
                cocktailId = configuration.id,
                componentContext = componentContext
            )
        }
    }

    @Serializable
    private sealed interface Configuration {
        @Serializable
        data object List : Configuration

        @Serializable
        data class Details(val id: Long) : Configuration
    }
}