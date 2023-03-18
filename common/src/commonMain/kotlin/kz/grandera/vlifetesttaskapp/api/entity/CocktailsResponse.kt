package kz.grandera.vlifetesttaskapp.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CocktailsResponse(
    @SerialName("drinks")
    val cocktails: List<CocktailEntity>
)