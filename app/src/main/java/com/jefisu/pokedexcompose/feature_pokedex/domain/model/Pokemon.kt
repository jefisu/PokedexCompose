package com.jefisu.pokedexcompose.feature_pokedex.domain.model

import com.jefisu.pokedexcompose.feature_pokedex.data.remote.response.Stat

data class Pokemon(
    val name: String = "",
    val imageUrl: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val types: List<String> = emptyList(),
    val moves: List<String> = emptyList(),
    val stats: List<Stat> = emptyList(),
    val abilities: List<String> = emptyList(),
    val id: Int? = null,
    val isFavorite: Boolean = false
)
