package com.jefisu.pokedexcompose.feature_pokedex.presentation.pokemon_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.pokedexcompose.core.util.Constants.PAGE_SIZE
import com.jefisu.pokedexcompose.core.util.Resource
import com.jefisu.pokedexcompose.core.util.UiText
import com.jefisu.pokedexcompose.feature_pokedex.domain.use_case.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    private var curPage = 0

    var state by mutableStateOf(PokemonListState())
        private set

    init {
        getPokemons()
    }

    fun getPokemons() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            when (val result = pokemonUseCase.getPokemons(PAGE_SIZE, curPage * PAGE_SIZE)) {
                is Resource.Success -> {
                    state = state.copy(
                        pokemons = result.data!!,
                        isLoading = false
                    )
                    curPage++
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        hasError = true,
                        errorMessage = result.uiText ?: UiText.unknownError()
                    )
                }
            }
        }
    }
}