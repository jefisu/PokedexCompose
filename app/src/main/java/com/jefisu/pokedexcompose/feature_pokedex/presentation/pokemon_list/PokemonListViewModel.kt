package com.jefisu.pokedexcompose.feature_pokedex.presentation.pokemon_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.pokedexcompose.core.util.Constants.CURRENT_OFFSET
import com.jefisu.pokedexcompose.core.util.Resource
import com.jefisu.pokedexcompose.core.util.UiText
import com.jefisu.pokedexcompose.feature_pokedex.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private var currentPageSize = 10

    var state by mutableStateOf(PokemonListState())
        private set

    init {
        getPokemons()
    }

    fun getPokemons() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            when (val result = repository.getPokemons(currentPageSize, CURRENT_OFFSET)) {
                is Resource.Success -> {
                    state = state.copy(
                        pokemons = result.data ?: emptyList(),
                        isLoading = false,
                        hasError = false
                    )
                    currentPageSize += 5
                }
                is Resource.Error -> {
                    state = state.copy(
                        pokemons = result.data ?: emptyList(),
                        isLoading = false,
                        hasError = true,
                        errorMessage = result.uiText ?: UiText.unknownError()
                    )
                }
            }
        }
    }
}