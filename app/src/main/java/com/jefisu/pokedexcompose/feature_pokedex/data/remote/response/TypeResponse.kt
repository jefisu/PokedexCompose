package com.jefisu.pokedexcompose.feature_pokedex.data.remote.response

data class TypeResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<ResultX>
)