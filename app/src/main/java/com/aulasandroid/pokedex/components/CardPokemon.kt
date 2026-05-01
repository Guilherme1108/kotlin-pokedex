package com.aulasandroid.pokedex.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aulasandroid.pokedex.R
import com.aulasandroid.pokedex.model.Pokemon
import com.aulasandroid.pokedex.model.PokemonDetails
import com.aulasandroid.pokedex.utils.pokemonColor

@Composable
fun CardPokemon(pokemon: PokemonDetails) {

    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemon.id}.png"

    val typeName = pokemon.types.firstOrNull()?.type?.name
    val color = pokemonColor(typeName)

    Card(
        modifier = Modifier
            .size(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(255, 255, 255)
        ),
        border = BorderStroke(1.dp, color),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(end = 12.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "#${pokemon.id}",
                    color = color
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "pokemon",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(color),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = pokemon.name)
            }
        }
    }
}