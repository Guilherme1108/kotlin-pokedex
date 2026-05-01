package com.aulasandroid.pokedex.model

data class Pokemon (
    val name: String = "",
    val url: String = ""
)

data class PokemonDetails(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeSlot>
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String,
    val url: String
)