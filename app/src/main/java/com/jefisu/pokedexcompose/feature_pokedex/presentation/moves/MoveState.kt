package com.jefisu.pokedexcompose.feature_pokedex.presentation.moves

import com.jefisu.pokedexcompose.core.util.UiText
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.MoveEntry

data class MoveState(
    val moves: List<MoveEntry> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: UiText = UiText.unknownError()
)
