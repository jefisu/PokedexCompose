package com.jefisu.pokedexcompose.feature_pokedex.presentation.types

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.pokedexcompose.core.util.Resource
import com.jefisu.pokedexcompose.core.util.UiText
import com.jefisu.pokedexcompose.feature_pokedex.domain.use_case.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    var state by mutableStateOf(TypeState())
        private set

    init {
        getTypes()
    }

    private fun getTypes() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            when (val result = pokemonUseCase.getTypesPokemon()) {
                is Resource.Success -> {
                    delay(500L)
                    state = state.copy(
                        types = result.data!!,
                        isLoading = false
                    )
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