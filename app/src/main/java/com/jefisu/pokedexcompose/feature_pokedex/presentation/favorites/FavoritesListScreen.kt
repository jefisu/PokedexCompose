package com.jefisu.pokedexcompose.feature_pokedex.presentation.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.core.components.PokemonItem
import com.jefisu.pokedexcompose.core.components.TopBar
import com.jefisu.pokedexcompose.core.presentation.StandardLoadingErrorScreen
import com.jefisu.pokedexcompose.core.util.Screen

@ExperimentalMaterialApi
@Composable
fun FavoritesListScreen(
    navController: NavController,
    viewModel: FavoritesListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = stringResource(R.string.favorites),
            onClickIcon1 = navController::navigateUp,
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.pokemonsFav) { pokemon ->
                PokemonItem(
                    pokemonInfo = pokemon,
                    onClick = {
                        navController.navigate(Screen.Detail.navArgs(pokemon.name))
                    }
                )
            }
        }
    }
    StandardLoadingErrorScreen(
        hasError = state.pokemonsFav.isEmpty(),
        errorMessage = stringResource(R.string.there_are_no_saved_items)
    )
}