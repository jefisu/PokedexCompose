package com.jefisu.pokedexcompose.feature_pokedex.presentation.home

import com.jefisu.pokedexcompose.core.util.UiText
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon

data class HomeState(
    val pokemon: Pokemon = Pokemon(),
    val query: String = "",
    val isVisiblePokemon: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: UiText = UiText.unknownError(),
    val hasError: Boolean = false
)
