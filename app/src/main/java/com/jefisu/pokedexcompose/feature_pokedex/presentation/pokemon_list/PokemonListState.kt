package com.jefisu.pokedexcompose.feature_pokedex.presentation.pokemon_list

import com.jefisu.pokedexcompose.core.util.UiText
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon

data class PokemonListState(
    val pokemons: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: UiText = UiText.unknownError()
)
