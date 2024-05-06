package kz.grandera.vlifetesttaskapp.features.list.store

import kotlin.coroutines.CoroutineContext

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor

import kz.grandera.vlifetesttaskapp.api.cocktails.CocktailsApi
import kz.grandera.vlifetesttaskapp.api.entity.CocktailEntity
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.State
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.Intent
import kz.grandera.vlifetesttaskapp.features.list.store.CocktailsListStore.Cocktail

private sealed interface Action : JvmSerializable {
    data object LoadCocktails : Action
}

private sealed interface Message : JvmSerializable {
    data object ErrorOccurred : Message
    data class LoadingChanged(val isLoading: Boolean) : Message
    data class CocktailsLoaded(val cocktails: List<Cocktail>) : Message
    data class CocktailsChanged(val cocktails: List<Cocktail>) : Message
    data class RefreshingChanged(val isRefreshing: Boolean) : Message
    data class SearchQueryChanged(val text: String) : Message
}

private fun reducer(): Reducer<State, Message> = Reducer { message ->
    when (message) {
        is Message.ErrorOccurred -> copy(isError = true, isLoading = false)
        is Message.LoadingChanged -> copy(isLoading = message.isLoading)
        is Message.CocktailsLoaded -> copy(
            isError = false,
            isLoading = false,
            cocktails = cocktails + message.cocktails,
            filteredCocktails = message.cocktails
        )
        is Message.CocktailsChanged -> copy(
            isError = false,
            filteredCocktails = message.cocktails
        )
        is Message.RefreshingChanged -> copy(isRefreshing = message.isRefreshing)
        is Message.SearchQueryChanged -> copy(searchQuery = message.text)
    }
}

internal fun CocktailsListStore(
    storeFactory: StoreFactory,
    mainContext: CoroutineContext,
    ioContext: CoroutineContext,
    cocktailsApi: CocktailsApi,
): CocktailsListStore = object : CocktailsListStore,
    Store<Intent, State, Nothing> by storeFactory.create(
        name = "CocktailsListStore",
        initialState = State(
            isError = false,
            isLoading = false,
            isRefreshing = false,
            searchQuery = "",
            cocktails = emptyList(),
            filteredCocktails = emptyList()
        ),
        reducer = reducer(),
        bootstrapper = SimpleBootstrapper(Action.LoadCocktails),
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
    private var fetchCocktailsJob: Job? = null

    override fun executeAction(action: Action) {
        when (action) {
            is Action.LoadCocktails -> {
                executeIntent(
                    intent = Intent.Filter(
                        isAlcoholic = false
                    )
                )
            }
        }
    }

    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.Shuffle -> {
                scope.launch {
                    dispatch(
                        message = Message.RefreshingChanged(
                            isRefreshing = true
                        )
                    )
                    delay(1000L)
                    dispatch(
                        message = Message.CocktailsChanged(
                            cocktails = state().filteredCocktails
                                .shuffled()
                        )
                    )
                    dispatch(
                        message = Message.RefreshingChanged(
                            isRefreshing = false
                        )
                    )
                }
            }
            is Intent.Search -> {
                dispatch(
                    message = Message.SearchQueryChanged(
                        text = intent.query
                    )
                )
                dispatch(
                    message = Message.CocktailsChanged(
                        cocktails = state().cocktails
                            .filter { cocktail ->
                                cocktail.name.startsWith(
                                    prefix = intent.query,
                                    ignoreCase = true
                                )
                            }
                    )
                )
            }
            is Intent.Filter -> {
                val cocktails = state().cocktails
                val filterOutAlcoholic = intent.isAlcoholic

                if (cocktails.any { cocktail -> cocktail.isAlcoholic == filterOutAlcoholic }) {
                    dispatch(
                        message = Message.CocktailsChanged(
                            cocktails = cocktails
                                .filter { cocktail -> cocktail.isAlcoholic == filterOutAlcoholic }
                        )
                    )
                } else {
                    fetchCocktailsJob?.cancel()
                    fetchCocktailsJob = scope.launch {
                        try {
                            dispatch(
                                message = Message.LoadingChanged(
                                    isLoading = true
                                )
                            )
                            val cocktailsResponse = withContext(context = ioContext) {
                                cocktailsApi.getCocktails(
                                    isAlcoholic = intent.isAlcoholic
                                )
                            }
                            dispatch(
                                message = Message.CocktailsLoaded(
                                    cocktails = cocktailsResponse.cocktails
                                        .map { cocktail ->
                                            cocktail.toCocktail(
                                                isAlcoholic = filterOutAlcoholic
                                            )
                                        }
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
    }
}

private fun CocktailEntity.toCocktail(isAlcoholic: Boolean): Cocktail =
    Cocktail(
        id = id.toLong(),
        name = name,
        imageUrl = imageUrl,
        isAlcoholic = isAlcoholic
    )