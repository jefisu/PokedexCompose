package com.jefisu.pokedexcompose.feature_pokedex.presentation.moves

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
class MoveViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    private val curPage = 0

    var state by mutableStateOf(MoveState())
        private set

    init {
        getMoves()
    }

    private fun getMoves() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            when (val result = pokemonUseCase.getMoves(PAGE_SIZE, curPage * PAGE_SIZE)) {
                is Resource.Success -> {
                    state = state.copy(
                        moves = result.data!!.groupBy { it.type },
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