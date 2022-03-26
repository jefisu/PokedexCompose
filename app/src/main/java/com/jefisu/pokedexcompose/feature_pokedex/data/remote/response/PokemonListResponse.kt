package com.jefisu.pokedexcompose.feature_pokedex.data.remote.response

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)