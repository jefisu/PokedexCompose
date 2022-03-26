package com.jefisu.pokedexcompose.core.util

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Detail : Screen("detail_screen")
    object Type : Screen("type_screen")
    object PokemonList : Screen("pokemon_list_screen")
    object Move : Screen("move_screen")
    object Favorite : Screen("favorite_screen")

    fun navArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}