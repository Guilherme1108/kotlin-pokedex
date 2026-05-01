package com.aulasandroid.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.aulasandroid.pokedex.screens.pokedex.PokedexScreen
import com.aulasandroid.pokedex.screens.pokedex.PokedexScreenViewModel
import com.aulasandroid.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    PokedexScreen(
                        modifier = Modifier.padding(innerPadding),
                        pokedexScreenViewModel = PokedexScreenViewModel()
                    )
                }
            }
        }
    }
}