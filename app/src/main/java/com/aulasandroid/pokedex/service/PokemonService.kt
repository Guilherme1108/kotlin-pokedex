package com.aulasandroid.pokedex.service

import androidx.compose.ui.geometry.Offset
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
    suspend fun getPokemon(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonResponse

    @GET
    suspend fun getPokemonDetailsByUrl(
        @Url url: String
    ): PokemonDetails

}