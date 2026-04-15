package com.aulasandroid.pokedex.screens.pokedex

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import com.aulasandroid.pokedex.model.Pokemon
import com.aulasandroid.pokedex.service.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import androidx.lifecycle.viewModelScope
import com.aulasandroid.pokedex.model.PokemonDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.log

class PokedexScreenViewModel : ViewModel() {

    private val limit = 20
    private var currentOffset = 0

    val listaPokemons = mutableStateOf<List<Pokemon>>(emptyList())

    val listaPokemonsDetails = mutableStateOf<List<PokemonDetails>>(emptyList())

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getPokemons(){
        if (_isLoading.value) return

        viewModelScope.launch {
            _isLoading.value = true

                try {
                    val response = RetrofitFactory()
                        .getPokemonService()
                        .getPokemon(limit = limit, offset = currentOffset)
                        .results

                    listaPokemons.value = listaPokemons.value + response

                    Log.i("TESTE", "Lista carregada: ${response.size}")

                    currentOffset += limit
                    getPokemonDetails(response)

                } catch (e: Exception) {
                    Log.e("ERROR", e.message ?: "")
                } finally {
                    _isLoading.value = false
                }


        }
    }

    fun getPokemonDetails(novosPokemons: List<Pokemon>) {
        viewModelScope.launch {
            try {

                val detalhes = novosPokemons.map { pokemon ->
                    RetrofitFactory()
                        .getPokemonService()
                        .getPokemonDetailsByUrl(pokemon.url)
                }

                listaPokemonsDetails.value = detalhes

            } catch (e: Exception) {
                Log.e("ERROR", e.message ?: "")
            }
        }
    }
}