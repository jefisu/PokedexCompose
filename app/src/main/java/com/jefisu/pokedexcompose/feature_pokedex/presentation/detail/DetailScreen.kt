package com.jefisu.pokedexcompose.feature_pokedex.presentation.detail

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.jefisu.pokedexcompose.core.components.TopBar
import com.jefisu.pokedexcompose.core.presentation.StandardLoadingErrorScreen
import com.jefisu.pokedexcompose.core.util.asString
import com.jefisu.pokedexcompose.feature_pokedex.presentation.detail.components.BasicInfoPokemon
import com.jefisu.pokedexcompose.feature_pokedex.presentation.detail.components.PokemonInfo
import com.jefisu.pokedexcompose.feature_pokedex.util.Page
import com.jefisu.pokedexcompose.feature_pokedex.util.parseTypeToColor

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun DetailScreen(
    navController: NavController,
    context: Context,
    pagerState: PagerState,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val typeColor = if (state.pokemon.types.isNotEmpty()) {
        parseTypeToColor(state.pokemon.types.first())
    } else return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(typeColor)
    ) {
        TopBar(
            icon2 = Icons.Default.Favorite,
            selected = state.pokemon.isFavorite,
            onClickIcon1 = navController::navigateUp,
            onClickIcon2 = {
                viewModel.onEvent(DetailEvent.SaveDeleteFavoritePokemon(state.pokemon.isFavorite))
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        BasicInfoPokemon(
            name = state.pokemon.name.replaceFirstChar { it.titlecase() },
            index = state.pokemon.id.toString(),
            types = state.pokemon.types
        )
        Spacer(modifier = Modifier.height(8.dp))
        PokemonInfo(
            pagerState = pagerState,
            backgroundColor = typeColor,
            pages = listOf(Page.About, Page.BaseStats, Page.Moves),
            pokemonInfo = state.pokemon
        )
    }
    StandardLoadingErrorScreen(
        hasError = state.hasError,
        errorMessage = state.errorMessage.asString(context)
    )
}