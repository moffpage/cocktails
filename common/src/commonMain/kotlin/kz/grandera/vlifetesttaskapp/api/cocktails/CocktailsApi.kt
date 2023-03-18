package kz.grandera.vlifetesttaskapp.api.cocktails

import kz.grandera.vlifetesttaskapp.api.entity.CocktailsResponse

internal interface CocktailsApi {
    suspend fun getCocktails(isAlcoholic: Boolean): CocktailsResponse
    suspend fun getCocktailDetails(cocktailId: Long): CocktailsResponse
}