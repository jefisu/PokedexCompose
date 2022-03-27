package com.jefisu.pokedexcompose.feature_pokedex.presentation.home

import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.core.components.LoadingAnimation
import com.jefisu.pokedexcompose.core.components.PokemonItem
import com.jefisu.pokedexcompose.core.presentation.StandardLoadingErrorScreen
import com.jefisu.pokedexcompose.core.util.Screen
import com.jefisu.pokedexcompose.core.util.asString
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.presentation.home.components.CustomCard
import com.jefisu.pokedexcompose.feature_pokedex.presentation.home.components.SearchBar
import com.jefisu.pokedexcompose.feature_pokedex.util.parseTypeToColor
import com.jefisu.pokedexcompose.ui.theme.LightBlue
import com.jefisu.pokedexcompose.ui.theme.LightGreen
import com.jefisu.pokedexcompose.ui.theme.LightPurple

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController,
    context: Context,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
        Text(
            text = stringResource(R.string.what_are_you_looking_for),
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(12.dp))
        SearchBar(
            text = state.query,
            hint = "Search pokemon",
            onSearch = { viewModel.onEvent(HomeEvent.GetSearchPokemon(it)) },
            clearText = { viewModel.onEvent(HomeEvent.ClearSearchText) }
        )
        Spacer(modifier = Modifier.height(12.dp))
        AnimatedContent(targetState = state.query) {
            Column(modifier = Modifier.fillMaxWidth()) {
                when {
                    state.pokemon.name.isNotBlank() -> {
                        PokemonItem(
                            pokemonInfo = state.pokemon,
                            boxColor = if (state.pokemon.name.isNotBlank()) parseTypeToColor(state.pokemon.types.first()) else Color.Transparent,
                            onClick = {
                                navController.navigate(Screen.Detail.navArgs(state.pokemon.name))
                            }
                        )
                    }
                    state.query.isBlank() -> {
                        CustomCard(
                            text = stringResource(R.string.pokemon),
                            img = R.drawable.ic_pokeball,
                            onClick = { navController.navigate(Screen.PokemonList.route) },
                            imgModifier = Modifier
                                .size(90.dp)
                                .graphicsLayer {
                                    rotationZ = 28f
                                    translationY = 40f
                                    translationX = 40f
                                }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        CustomCard(
                            text = stringResource(R.string.favorites),
                            img = R.drawable.ic_favorite,
                            onClick = { navController.navigate(Screen.Favorite.route) },
                            backgroundColor = LightPurple,
                            imgModifier = Modifier
                                .size(90.dp)
                                .graphicsLayer {
                                    rotationZ = -30f
                                    translationY = 40f
                                    translationX = 40f
                                }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row {
                            CustomCard(
                                text = stringResource(R.string.types),
                                modifier = Modifier.weight(0.5f),
                                backgroundColor = LightGreen,
                                img = R.drawable.ic_grass,
                                onClick = {
                                    navController.navigate(Screen.Type.route)
                                },
                                imgModifier = Modifier
                                    .graphicsLayer {
                                        rotationZ = -80f
                                        translationY = 50f
                                        translationX = 50f
                                    }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            CustomCard(
                                text = stringResource(R.string.moves),
                                modifier = Modifier.weight(0.5f),
                                backgroundColor = LightBlue,
                                onClick = { navController.navigate(Screen.Move.route) },
                                img = R.drawable.ic_battle,
                                imgModifier = Modifier
                                    .graphicsLayer {
                                        rotationZ = -4f
                                        translationY = 40f
                                        translationX = 20f
                                    }
                            )
                        }
                    }
                    state.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            LoadingAnimation()
                        }
                    }
                    state.hasError -> {
                        StandardLoadingErrorScreen(
                            hasError = state.hasError,
                            errorMessage = state.errorMessage.asString(context)
                        )
                    }
                }
            }
        }
    }
}