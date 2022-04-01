package com.jefisu.pokedexcompose.feature_pokedex.presentation.pokemon_list

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.core.components.ListAnimation
import com.jefisu.pokedexcompose.core.components.PokemonItem
import com.jefisu.pokedexcompose.core.components.TopBar
import com.jefisu.pokedexcompose.core.presentation.StandardLoadingErrorScreen
import com.jefisu.pokedexcompose.core.util.Screen
import com.jefisu.pokedexcompose.core.util.asString

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PokemonListScreen(
    navController: NavController,
    context: Context,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            onClickIcon1 = navController::navigateUp,
            title = stringResource(R.string.pokedex)
        )
        ListAnimation(
            items = state.pokemons,
            isLoading = state.isLoading
        ) { index, pokemon ->
            val size = state.pokemons.size
            if (index >= size - 1 && !state.isLoading) {
                viewModel.getPokemons()
            }
            PokemonItem(
                pokemonInfo = pokemon,
                onClick = { navController.navigate(Screen.Detail.navArgs(pokemon.name)) }
            )
        }
    }
    StandardLoadingErrorScreen(
        hasError = state.hasError,
        errorMessage = state.errorMessage.asString(context),
        isLoading = state.isLoading,
        items = state.pokemons
    )
}