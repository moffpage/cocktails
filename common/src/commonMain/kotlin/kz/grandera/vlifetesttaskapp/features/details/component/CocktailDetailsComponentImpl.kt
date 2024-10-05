package kz.grandera.vlifetesttaskapp.features.details.component

import kotlin.coroutines.CoroutineContext

import org.koin.core.component.inject
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.instancekeeper.getStore

import kz.grandera.vlifetesttaskapp.api.cocktails.CocktailsApi
import kz.grandera.vlifetesttaskapp.core.extensions.states
import kz.grandera.vlifetesttaskapp.features.details.store.CocktailDetailsStore
import kz.grandera.vlifetesttaskapp.features.details.store.CocktailDetailsStore.State
import kz.grandera.vlifetesttaskapp.features.details.store.CocktailDetailsStore.Intent
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent.Model
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent.DrinkCategory

internal class CocktailDetailsComponentImpl(
    id: Long,
    componentContext: ComponentContext,
    private val onNavigateBack: () -> Unit,
) : CocktailDetailsComponent,
    KoinComponent,
    ComponentContext by componentContext
{
    private val mainContext by inject<CoroutineContext>(qualifier = named(name = "Main"))
    private val ioContext by inject<CoroutineContext>(qualifier = named(name = "IO"))
    private val cocktailsApi by inject<CocktailsApi>()
    private val storeFactory by inject<StoreFactory>()

    private val store = instanceKeeper.getStore {
        CocktailDetailsStore(
            cocktailId = id,
            storeFactory = storeFactory,
            mainContext = mainContext,
            ioContext = ioContext,
            cocktailsApi = cocktailsApi
        )
    }

    override val model: Value<Model> = store.states
        .map { cocktail -> cocktail.toModel() }

    override fun navigateBack() {
        onNavigateBack()
    }

    override fun refetchDetails() {
        store.accept(intent = Intent.Refresh)
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