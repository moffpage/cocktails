package kz.grandera.vlifetesttaskapp.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CocktailEntity(
    @SerialName("idDrink")
    val id: String,

    @SerialName("strDrink")
    val name: String,

    @SerialName("strDrinkThumb")
    val imageUrl: String,

    @SerialName("strCategory")
    val category: String?,

    @SerialName("strGlass")
    val glassType: String?,

    @SerialName("strAlcoholic")
    val alcoholIndication: String?,

    @SerialName("strInstructions")
    val preparationInstruction: String?,
)