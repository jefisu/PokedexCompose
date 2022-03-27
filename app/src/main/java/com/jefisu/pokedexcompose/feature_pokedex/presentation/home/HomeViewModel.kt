package com.jefisu.pokedexcompose.feature_pokedex.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.pokedexcompose.core.util.Resource
import com.jefisu.pokedexcompose.core.util.UiText
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.domain.use_case.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private var jobPokemon: Job? = null

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetSearchPokemon -> {
                if (event.query.isNotEmpty()) {
                    state = state.copy(query = event.query, isLoading = true)
                    jobPokemon?.cancel()
                    jobPokemon = viewModelScope.launch {
                        delay(300)
                        when (val result =
                            pokemonUseCase.getPokemonByName(event.query.lowercase())) {
                            is Resource.Success -> {
                                state = state.copy(
                                    pokemon = result.data!!,
                                    isLoading = false
                                )
                            }
                            is Resource.Error -> {
                                state = state.copy(
                                    hasError = true,
                                    errorMessage = result.uiText ?: UiText.unknownError(),
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
            }
            is HomeEvent.ClearSearchText -> {
                state = state.copy(
                    pokemon = Pokemon(),
                    query = ""
                )
            }
        }
    }
}