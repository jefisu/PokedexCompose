package com.jefisu.pokedexcompose.feature_pokedex.util

sealed class Page(val title: String) {
    object About: Page("About")
    object BaseStats: Page("Base Stats")
    object Moves: Page("Moves")
}
