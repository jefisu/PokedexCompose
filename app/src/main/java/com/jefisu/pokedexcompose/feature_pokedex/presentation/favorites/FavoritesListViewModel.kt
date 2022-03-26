package com.jefisu.pokedexcompose.feature_pokedex.presentation.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.pokedexcompose.feature_pokedex.domain.use_case.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesListViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    var state by mutableStateOf(FavoriteListState())
        private set

    init {
        getFavoritesPokemon()
    }

    private fun getFavoritesPokemon() {
        viewModelScope.launch {
            pokemonUseCase.getFavoritePokemons()
                .onEach {
                   state = state.copy(
                       pokemonsFav = it
                   )
                }.launchIn(this)
        }
    }
}