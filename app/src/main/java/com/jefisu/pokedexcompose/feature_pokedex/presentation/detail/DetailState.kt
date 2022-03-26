package com.jefisu.pokedexcompose.feature_pokedex.presentation.detail

import com.jefisu.pokedexcompose.core.util.UiText
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon

data class DetailState(
    val pokemon: Pokemon = Pokemon(),
    val hasError: Boolean = false,
    val errorMessage: UiText = UiText.unknownError()
)
