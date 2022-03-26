package com.jefisu.pokedexcompose.feature_pokedex.presentation.favorites

import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon

data class FavoriteListState(
    val pokemonsFav: List<Pokemon> = emptyList()
)
