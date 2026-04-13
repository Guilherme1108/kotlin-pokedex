package com.aulasandroid.pokedex.screens.pokedex

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Vertices
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aulasandroid.pokedex.R
import com.aulasandroid.pokedex.components.CardPokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun PokedexScreen(modifier: Modifier = Modifier, pokedexScreenViewModel: PokedexScreenViewModel) {

    var pokemonState by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        pokedexScreenViewModel.getPokemons()
    }


    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(Color(218, 60, 57))
                .padding(start = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(R.drawable.pokebola),
                contentDescription = "Pokebola"
            )

            Spacer(modifier = Modifier.width(24.dp))

            Text(
                text = "Pokédex",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = pokemonState,
                    onValueChange = { pokemonState = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    ),
                    label = { Text(text = "Nome ou ID") },
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = {
                        IconButton(onClick = {
                            /*todo*/
                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = ""
                            )
                        }
                    }
                )

            }

            Spacer(modifier = Modifier.height(32.dp))

            val pokemons = pokedexScreenViewModel.listaPokemons.value

            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(pokemons) { pokemon ->
                    Log.i("TESTE", "${pokemons}")
                    CardPokemon(pokemon)
                }
            }

        }


    }
}