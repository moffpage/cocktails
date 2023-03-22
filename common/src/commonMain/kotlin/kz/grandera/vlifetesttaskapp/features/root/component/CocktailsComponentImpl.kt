package kz.grandera.vlifetesttaskapp.features.root.component

import org.koin.core.component.inject
import org.koin.core.component.KoinComponent

import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.mvikotlin.timetravel.export.TimeTravelExportSerializer

import kz.grandera.vlifetesttaskapp.utils.FileManager
import kz.grandera.vlifetesttaskapp.utils.PlatformContext
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponent.Child
import kz.grandera.vlifetesttaskapp.features.root.component.CocktailsComponentImpl.Configuration
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponent
import kz.grandera.vlifetesttaskapp.features.list.component.CocktailsListComponentImpl
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponentImpl
import kz.grandera.vlifetesttaskapp.features.timetravel.component.TimeTravelClientComponent
import kz.grandera.vlifetesttaskapp.features.timetravel.component.TimeTravelClientComponentImpl

internal class CocktailsComponentImpl(
    componentContext: ComponentContext,
    private val fileManager: FileManager,
    private val platformContext: PlatformContext
) : CocktailsComponent,
    KoinComponent,
    ComponentContext by componentContext
{
    private val timeTravelExportSerializer by inject<TimeTravelExportSerializer>()

    private val navigation = StackNavigation<Configuration>()
    private val childStack = childStack(
        source = navigation,
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

    internal fun listComponent(componentContext: ComponentContext): CocktailsListComponent =
        CocktailsListComponentImpl(
            componentContext = componentContext,
            onShowCocktail = { cocktailId ->
                navigation.push(
                    configuration = Configuration.Details(
                        id = cocktailId
                    )
                )
            },
            onViewTimeTravel = {
                navigation.push(
                    configuration = Configuration.TimeTravelClient
                )
            }
        )

    internal fun detailsComponent(id: Long, componentContext: ComponentContext): CocktailDetailsComponent =
        CocktailDetailsComponentImpl(
            id = id,
            componentContext = componentContext,
            onNavigateBack = { navigation.pop() }
        )

    internal fun timeTravelClientComponent(componentContext: ComponentContext): TimeTravelClientComponent =
        TimeTravelClientComponentImpl(
            componentContext = componentContext,
            fileManager = fileManager,
            platformContext = platformContext,
            serializer = timeTravelExportSerializer,
            onNavigateBack = { navigation.pop() }
        )

    internal sealed interface Configuration : Parcelable {
        @Parcelize
        object List : Configuration

        @Parcelize
        data class Details(val id: Long) : Configuration

        @Parcelize
        object TimeTravelClient : Configuration
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
        is Configuration.TimeTravelClient -> Child.TimeTravelLookOver(
            component = timeTravelClientComponent(
                componentContext = componentContext
            )
        )
    }