package com.jefisu.pokedexcompose.core.util

sealed class UiEvent {
    data class ShowSnackBar(val uiText: UiText) : UiEvent()
}