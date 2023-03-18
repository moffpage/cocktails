package kz.grandera.vlifetesttaskapp.api.cocktails

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

import kz.grandera.vlifetesttaskapp.api.entity.CocktailsResponse

internal class CocktailsApiClient(private val delegate: HttpClient) : CocktailsApi {
    override suspend fun getCocktails(isAlcoholic: Boolean): CocktailsResponse =
        delegate.get(
            urlString = divideBy(
                DivisionMethod.Filter(
                    isAlcoholic = isAlcoholic
                )
            )
        ).body()

    override suspend fun getCocktailDetails(cocktailId: Long): CocktailsResponse =
        delegate.get(urlString = "lookup.php?i=$cocktailId").body()

    private companion object {
        fun divideBy(vararg methods: DivisionMethod): String =
            methods.joinToString(separator = "&") { method ->
                method.path +
                    ".php?" +
                    method.parameters.entries.joinToString(separator = "=")
            }
    }
}

private sealed interface DivisionMethod {
    val path: String
    val parameters: Map<String, String>

    data class Filter(
        val isAlcoholic: Boolean?
    ) : DivisionMethod {
        override val path: String = "filter"
        override val parameters: Map<String, String> = mutableMapOf<String, String>()
            .apply {
                isAlcoholic?.let { isAlcoholic ->
                    put(
                        key = "a",
                        value = if (isAlcoholic) "Alcoholic" else "Non_Alcoholic"
                    )
                }
            }
    }
}