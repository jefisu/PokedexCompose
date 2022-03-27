package com.jefisu.pokedexcompose.feature_pokedex.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.pokedexcompose.core.util.Resource
import com.jefisu.pokedexcompose.core.util.UiText
import com.jefisu.pokedexcompose.feature_pokedex.domain.use_case.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    var state by mutableStateOf(DetailState())
        private set

    init {
        savedStateHandle.get<String>("name")?.let {
            onEvent(DetailEvent.GetSavedPokemon(it))
        }
    }

    fun onEvent(event: DetailEvent) {
        viewModelScope.launch {
            when (event) {
                is DetailEvent.GetPokemonDetail -> {
                    when (val result = pokemonUseCase.getPokemonByName(event.name)) {
                        is Resource.Success -> {
                            state = state.copy(
                                pokemon = result.data!!
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                hasError = true,
                                errorMessage = result.uiText ?: UiText.unknownError()
                            )
                        }
                    }
                }
                is DetailEvent.GetSavedPokemon -> {
                    val result = pokemonUseCase.getFavoritePokemon(event.name)
                    if (result == null) onEvent(DetailEvent.GetPokemonDetail(event.name))
                    result?.let {
                        state = state.copy(pokemon = it)
                    }
                }
                is DetailEvent.SaveDeleteFavoritePokemon -> {
                    state = state.copy(pokemon = state.pokemon.copy(isFavorite = !event.selected))
                    when(event.selected) {
                        true -> pokemonUseCase.deleteFavoritePokemon(state.pokemon)
                        else -> pokemonUseCase.insertFavoritePokemon(state.pokemon)
                    }
                }
            }
        }
    }
}