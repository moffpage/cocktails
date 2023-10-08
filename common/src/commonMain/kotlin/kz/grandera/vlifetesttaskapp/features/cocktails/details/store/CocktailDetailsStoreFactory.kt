package kz.grandera.vlifetesttaskapp.features.cocktails.details.store

import kotlin.coroutines.CoroutineContext

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor

import kz.grandera.vlifetesttaskapp.api.cocktails.CocktailsApi
import kz.grandera.vlifetesttaskapp.features.cocktails.details.store.CocktailDetailsStore.State
import kz.grandera.vlifetesttaskapp.features.cocktails.details.store.CocktailDetailsStore.Intent

private sealed interface Action : JvmSerializable {
    data class LoadCocktail(val cocktailId: Long) : Action
}

private sealed interface Message : JvmSerializable {
    data object ErrorOccurred : Message
    data class CocktailDetailsLoaded(
        val name: String,
        val category: String,
        val imageUrl: String,
        val glassType: String,
        val isAlcoholic: Boolean,
        val preparationsInstruction: String,
    ) : Message
}

internal fun CocktailDetailsStore(
    cocktailId: Long,
    storeFactory: StoreFactory,
    mainContext: CoroutineContext,
    ioContext: CoroutineContext,
    cocktailsApi: CocktailsApi
): CocktailDetailsStore =
    object : CocktailDetailsStore, Store<Intent, State, Nothing>
    by storeFactory.create<Intent, Action, Message, State, Nothing>(
        name = "CocktailDetailsStore",
        reducer = { message ->
            when (message) {
                is Message.ErrorOccurred -> copy(isError = true)
                is Message.CocktailDetailsLoaded -> copy(
                    isError = false,
                    category = message.category,
                    imageUrl = message.imageUrl,
                    glassType = message.glassType,
                    isAlcoholic = message.isAlcoholic,
                    cocktailName = message.name,
                    preparationInstruction = message.preparationsInstruction
                )
            }
        },
        initialState = State(
            cocktailId = cocktailId,
            isError = false,
            category = "",
            imageUrl = "",
            glassType = "",
            isAlcoholic = false,
            cocktailName = "",
            preparationInstruction = ""
        ),
        bootstrapper = SimpleBootstrapper(
            Action.LoadCocktail(
                cocktailId = cocktailId
            )
        ),
        executorFactory = {
            ExecutorImpl(
                mainContext = mainContext,
                ioContext = ioContext,
                cocktailsApi = cocktailsApi
            )
        }
    ) { }

private class ExecutorImpl(
    mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
    private val cocktailsApi: CocktailsApi
) : CoroutineExecutor<Intent, Action, State, Message, Nothing>(
    mainContext = mainContext
) {
    private var fetchCocktailDetailsJob: Job? = null

    override fun executeAction(action: Action, getState: () -> State) {
        when (action) {
            is Action.LoadCocktail -> {
                fetchCocktailDetailsJob?.cancel()
                fetchCocktailDetailsJob = scope.launch {
                    try {
                        val cocktailDetails = withContext(context = ioContext) {
                            cocktailsApi.getCocktailDetails(
                                cocktailId = action.cocktailId
                            ).cocktails.first()
                        }
                        dispatch(
                            message = Message.CocktailDetailsLoaded(
                                name = cocktailDetails.name,
                                category = cocktailDetails.category.orEmpty(),
                                imageUrl = cocktailDetails.imageUrl,
                                glassType = cocktailDetails.glassType.orEmpty(),
                                isAlcoholic = cocktailDetails.alcoholIndication == "Alcoholic",
                                preparationsInstruction = cocktailDetails.preparationInstruction.orEmpty()
                            )
                        )
                    } catch (exception: Exception) {
                        dispatch(
                            message = Message.ErrorOccurred
                        )
                    }
                }
            }
        }
    }

    override fun executeIntent(intent: Intent, getState: () -> State) {
        when (intent) {
            is Intent.Refresh -> {
                executeAction(
                    action = Action.LoadCocktail(
                        cocktailId = getState().cocktailId
                    )
                )
            }
        }
    }
}