package com.aulasandroid.pokedex.utils

import androidx.compose.ui.graphics.Color

fun pokemonColor(type: String?): Color {
    return when (type?.lowercase()) {
        "grass" -> Color(116, 203, 72)
        "fire" -> Color(245, 125, 49)
        "water" -> Color(100, 147, 235)
        "bug" -> Color(167, 183, 35)
        "electric" -> Color(249, 207, 48)
        "ghost" -> Color(112, 85, 155)
        "normal" -> Color(170, 166, 127)
        "psychic" -> Color(251, 85, 132)
        "steel" -> Color(183, 185, 208)
        "poison" -> Color(164, 62, 158)
        "ground" -> Color(222, 193, 107)
        "fairy" -> Color(230, 158, 172)
        "fighting" -> Color(193, 34, 57)
        "rock" -> Color(182, 158, 49)
        "ice" -> Color(154, 214, 223)
        "dragon" -> Color(112, 55, 255)
        "flying" -> Color(168, 145, 236)

        else -> Color(102, 102, 102)
    }
}