package com.jefisu.pokedexcompose.feature_pokedex.presentation.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.core.util.Screen
import com.jefisu.pokedexcompose.feature_pokedex.presentation.home.components.CustomCard
import com.jefisu.pokedexcompose.feature_pokedex.presentation.home.components.CustomTextField
import com.jefisu.pokedexcompose.ui.theme.LightBlue
import com.jefisu.pokedexcompose.ui.theme.LightGreen
import com.jefisu.pokedexcompose.ui.theme.LightPurple

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController
) {
    var text by remember { mutableStateOf("") }
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
        CustomTextField(
            text = text,
            onTextChange = { text = it },
            hint = "Search pokemons"
        )
        Spacer(modifier = Modifier.height(20.dp))
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
}