package com.aulasandroid.pokedex.service

import com.aulasandroid.pokedex.model.Pokemon
import com.aulasandroid.pokedex.model.PokemonDetails
import com.aulasandroid.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonService {
    @GET("pokemon")
    fun getPokemon(
        @Query("limit") limit: Int = 1025
    ): Call<PokemonResponse>

    @GET
    fun getPokemonDetailsByUrl(
        @Url url: String
    ): Call<PokemonDetails>

}