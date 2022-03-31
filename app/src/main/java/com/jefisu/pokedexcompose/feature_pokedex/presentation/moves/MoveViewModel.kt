package com.jefisu.pokedexcompose.feature_pokedex.presentation.moves

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.pokedexcompose.core.util.Constants.CURRENT_OFFSET
import com.jefisu.pokedexcompose.core.util.Resource
import com.jefisu.pokedexcompose.core.util.UiText
import com.jefisu.pokedexcompose.feature_pokedex.domain.use_case.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoveViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    private var currentPageSize = 0

    var state by mutableStateOf(MoveState())
        private set

    init {
        getMoves()
    }

    fun getMoves() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            val result = pokemonUseCase.getMoves(currentPageSize, CURRENT_OFFSET)
            when (result) {
                is Resource.Success -> {
                    state = state.copy(
                        moves = result.data!!,
                        isLoading = false,
                        hasError = false
                    )
                    currentPageSize += 10
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