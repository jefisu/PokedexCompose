package com.jefisu.pokedexcompose.feature_pokedex.presentation.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.core.components.PokemonItem
import com.jefisu.pokedexcompose.core.components.TopBar
import com.jefisu.pokedexcompose.core.util.Screen
import com.jefisu.pokedexcompose.ui.theme.spacing

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
        LazyColumn {
            items(
                items = state.pokemonsFav
            ) { pokemon ->
                PokemonItem(
                    pokemonInfo = pokemon,
                    onClick = {
                        navController.navigate(Screen.Detail.navArgs(pokemon.name))
                    }
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
        }
    }
    if (state.pokemonsFav.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.there_are_no_saved_items),
                style = MaterialTheme.typography.h5
            )
        }
    }
}