package com.jefisu.pokedexcompose.core.components

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.jefisu.pokedexcompose.core.util.Screen
import com.jefisu.pokedexcompose.feature_pokedex.presentation.detail.DetailScreen
import com.jefisu.pokedexcompose.feature_pokedex.presentation.favorites.FavoritesListScreen
import com.jefisu.pokedexcompose.feature_pokedex.presentation.home.HomeScreen
import com.jefisu.pokedexcompose.feature_pokedex.presentation.moves.MoveScreen
import com.jefisu.pokedexcompose.feature_pokedex.presentation.pokemon_list.PokemonListScreen
import com.jefisu.pokedexcompose.feature_pokedex.presentation.types.TypeScreen

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun Navigation(
    navController: NavHostController,
    pagerState: PagerState,
    context: Context
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            StartAnimation(
                enterTransition = slideInHorizontally() + fadeIn(),
                exitTransition = slideOutHorizontally() + fadeOut()
            ) {
                HomeScreen(navController, context)
            }
        }
        composable(
            route = Screen.Detail.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            )
        ) {
            StartAnimation(
                enterTransition = scaleIn() + fadeIn(),
                exitTransition = scaleOut() + fadeOut()
            ) {
                DetailScreen(navController, context, pagerState)
            }
        }
        composable(route = Screen.PokemonList.route) {
            StartAnimation(
                enterTransition = slideInHorizontally() + fadeIn(),
                exitTransition = slideOutHorizontally() + fadeOut()
            ) {
                PokemonListScreen(navController, context)
            }
        }
        composable(Screen.Move.route) {
            StartAnimation(
                enterTransition = slideInHorizontally() + fadeIn(),
                exitTransition = slideOutHorizontally() + fadeOut()
            ) {
                MoveScreen(navController, context)
            }
        }
        composable(Screen.Type.route) {
            StartAnimation(
                enterTransition = slideInHorizontally() + fadeIn(),
                exitTransition = slideOutHorizontally() + fadeOut()
            ) {
                TypeScreen(navController, context)
            }
        }
        composable(Screen.Favorite.route) {
            StartAnimation(
                enterTransition = slideInHorizontally() + fadeIn(),
                exitTransition = slideOutHorizontally() + fadeOut()
            ) {
                FavoritesListScreen(navController)
            }
        }
    }
}