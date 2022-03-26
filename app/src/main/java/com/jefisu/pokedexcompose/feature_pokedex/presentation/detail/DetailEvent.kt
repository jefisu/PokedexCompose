package com.jefisu.pokedexcompose.feature_pokedex.presentation.detail

sealed class DetailEvent {
    data class GetPokemonDetail(val name: String) : DetailEvent()
    data class GetSavedPokemon(val name: String) : DetailEvent()
    data class SaveDeleteFavoritePokemon(val selected: Boolean) : DetailEvent()
}
