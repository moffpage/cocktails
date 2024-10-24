package kz.grandera.vlifetesttaskapp.features.details.component

import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable

import org.koin.dsl.module
import org.koin.core.component.getScopeId
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore

import kz.grandera.vlifetesttaskapp.ui.details.CocktailDetailsContent
import kz.grandera.vlifetesttaskapp.core.event.back.BackEvent
import kz.grandera.vlifetesttaskapp.core.event.back.BackEventsProducerDelegate
import kz.grandera.vlifetesttaskapp.core.event.back.backEventsProducerDelegate
import kz.grandera.vlifetesttaskapp.core.scope.koinScope
import kz.grandera.vlifetesttaskapp.core.extensions.states
import kz.grandera.vlifetesttaskapp.core.componentcontext.AppComponentContext
import kz.grandera.vlifetesttaskapp.features.details.store.CocktailDetailsStore
import kz.grandera.vlifetesttaskapp.features.details.store.CocktailDetailsStore.State
import kz.grandera.vlifetesttaskapp.features.details.store.CocktailDetailsStore.Intent
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent.Model
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent.DrinkCategory

internal class CocktailDetailsComponentImpl(
    id: Long,
    componentContext: AppComponentContext
) : CocktailDetailsComponent,
    AppComponentContext by componentContext,
    BackEventsProducerDelegate by backEventsProducerDelegate()
{
    private val koinScope = koinScope(
        cocktailDetailsModule,
        scopeId = getScopeId(),
        qualifier = qualifier<CocktailDetailsComponent>()
    )

    private val storeFactory by koinScope.inject<CocktailDetailsStore>(
        parameters = {
            parametersOf(id)
        }
    )
    private val store = instanceKeeper.getStore { storeFactory }

    @Composable
    override fun Content(modifier: Modifier) {
        CocktailDetailsContent(
            component = this,
            modifier = modifier
        )
    }

    override val model: Value<Model> = store.states
        .map { cocktail -> cocktail.toModel() }

    override fun navigateBack() {
        dispatch(BackEvent)
    }

    override fun refetchDetails() {
        store.accept(intent = Intent.Refresh)
    }
}

private val cocktailDetailsModule = module {
    scope<CocktailDetailsComponent> {
        scoped<CocktailDetailsStore> { (cocktailId: Long) ->
            CocktailDetailsStore(
                cocktailId = cocktailId,
                storeFactory = get(),
                mainContext = get(qualifier = named("Main")),
                ioContext = get(qualifier = named("IO")),
                cocktailsApi = get()
            )
        }
    }
}

private fun State.toModel(): Model = Model(
    isError = this.isError,
    category = DrinkCategory.entries
        .firstOrNull { category -> category.name in this.category },
    imageUrl = this.imageUrl,
    glassType = this.glassType,
    isAlcoholic = this.isAlcoholic,
    cocktailName = this.cocktailName,
    preparationInstruction = this.preparationInstruction
)