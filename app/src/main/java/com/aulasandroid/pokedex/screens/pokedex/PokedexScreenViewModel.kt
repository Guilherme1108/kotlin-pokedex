package com.aulasandroid.pokedex.screens.pokedex

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aulasandroid.pokedex.model.Pokemon
import com.aulasandroid.pokedex.model.PokemonDetails
import com.aulasandroid.pokedex.service.RetrofitFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokedexScreenViewModel : ViewModel() {

    private val limit = 20
    private var currentOffset = 0

    private var isFetching = false

    private val pokemonService = RetrofitFactory().getPokemonService()

    private val _listaPokemonsDetails = mutableStateOf<List<PokemonDetails>>(emptyList())
    val listaPokemonsDetails: State<List<PokemonDetails>> = _listaPokemonsDetails

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getPokemons() {
        if (isFetching) return

        viewModelScope.launch {
            try {
                isFetching = true
                _isLoading.value = true

                val response = pokemonService.getPokemon(
                    limit = limit,
                    offset = currentOffset
                ).results

                if (response.isNotEmpty()) {
                    fetchDetailsAndAppend(response)

                    currentOffset += limit
                }

            } catch (e: Exception) {
                Log.e("POKEDEX", "Erro ao carregar lista: ${e.message}")
            } finally {
                _isLoading.value = false
                isFetching = false
            }
        }
    }

    private suspend fun fetchDetailsAndAppend(novosPokemons: List<Pokemon>) {
        try {
            val novosDetalhes = coroutineScope {
                novosPokemons.map { pokemon ->
                    async {
                        pokemonService.getPokemonDetailsByUrl(pokemon.url)
                    }
                }.awaitAll()
            }

            _listaPokemonsDetails.value = _listaPokemonsDetails.value + novosDetalhes

        } catch (e: Exception) {
            Log.e("POKEDEX", "Erro ao carregar detalhes: ${e.message}")
        }
    }
}