package com.aulasandroid.pokedex.model

data class Pokemon (
    val name: String = "",
    val url: String = ""
)

data class PokemonDetails (
    val id: Int,
    val name: String
)