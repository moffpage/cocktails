package kz.grandera.vlifetesttaskapp.features.dto

import kotlinx.serialization.Serializable

@Serializable
internal class CocktailDto(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val isAlcoholic: Boolean
)