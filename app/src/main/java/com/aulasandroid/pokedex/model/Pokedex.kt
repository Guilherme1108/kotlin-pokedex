package com.aulasandroid.pokedex.model

import com.aulasandroid.pokedex.service.RetrofitFactory
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import kotlin.collections.emptyList

class Pokedex {

    fun getPokemons(callback: (List<Pokemon>) -> Unit) {

        val call = RetrofitFactory()
            .getPokemonService()
            .getPokemon()

        call.enqueue(object : Callback<PokemonResponse> {

            override fun onResponse(
                call: Call<PokemonResponse>,
                response: retrofit2.Response<PokemonResponse>
            ) {
                val lista = response.body()?.results ?: emptyList()
                callback(lista)
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                callback(emptyList())
            }
        })
    }

//    fun getPokemonDetails(callback: (List<Pokemon>) -> Unit) {
//        var call = RetrofitFactory()
//            .getPokemonService()
//            .getPokemonDetailsByUrl()
//
//        call.enqueue(object : Callback {
//
//        })
//    }

}