package com.aulasandroid.pokedex.model

data class Pokemon (
    val name: String = "",
    val url: String = ""
)

data class PokemonDetails (
    val id: Int = 0,
    val image: String = "",
    val name: String = ""
)