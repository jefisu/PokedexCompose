package com.jefisu.pokedexcompose.feature_pokedex.presentation.types

import com.jefisu.pokedexcompose.core.util.UiText
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.TypeEntry

data class TypeState(
    val types: List<TypeEntry> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: UiText = UiText.unknownError()
)
