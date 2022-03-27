package com.jefisu.pokedexcompose.feature_pokedex.presentation.pokemon_list

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.core.components.PokemonItem
import com.jefisu.pokedexcompose.core.components.TopBar
import com.jefisu.pokedexcompose.core.presentation.StandardLoadingErrorScreen
import com.jefisu.pokedexcompose.core.util.Screen
import com.jefisu.pokedexcompose.core.util.asString
import com.jefisu.pokedexcompose.ui.theme.spacing

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.extraSmall)
    ) {
        TopBar(
            onClickIcon1 = navController::navigateUp,
            title = stringResource(R.string.pokedex)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        LazyColumn {
            val itemCount = if (state.pokemons.size % 2 == 0) {
                state.pokemons.size / 2
            } else {
                state.pokemons.size / 2 + 1
            }
            items(itemCount) { i ->
                if (i >= itemCount - 1) {
                    LaunchedEffect(key1 = true) {
                        viewModel.getPokemons()
                    }
                }
                PokemonItem(
                    pokemonInfo = state.pokemons[i],
                    onClick = {
                        navController.navigate(Screen.Detail.navArgs(state.pokemons[i].name))
                    }
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
        }
    }
    StandardLoadingErrorScreen(
        hasError = state.hasError,
        errorMessage = state.errorMessage.asString(context),
        isLoading = state.isLoading,
        items = state.pokemons
    )
}