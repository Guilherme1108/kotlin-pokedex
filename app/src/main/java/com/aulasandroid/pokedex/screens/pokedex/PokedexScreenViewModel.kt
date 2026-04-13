package com.aulasandroid.pokedex.screens.pokedex

import androidx.compose.runtime.mutableStateOf
import com.aulasandroid.pokedex.model.Pokedex
import com.aulasandroid.pokedex.model.Pokemon

class PokedexScreenViewModel {

    private val repository = Pokedex()

    val listaPokemons = mutableStateOf<List<Pokemon>>(emptyList())

    fun getPokemons() {
        repository.getPokemons { resultado ->

            listaPokemons.value = resultado

        }
    }
}