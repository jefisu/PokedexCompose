package com.jefisu.pokedexcompose.feature_pokedex.presentation.detail.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.util.Page
import com.jefisu.pokedexcompose.ui.theme.spacing

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun PokemonInfo(
    modifier: Modifier = Modifier,
    imgModifier: Modifier = Modifier,
    pokemonInfo: Pokemon,
    pagerState: PagerState,
    backgroundColor: Color,
    pages: List<Page>,
    boxCornerShape: Shape = RoundedCornerShape(32.dp, 32.dp)
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 180.dp)
                .clip(boxCornerShape)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = MaterialTheme.spacing.large - 8.dp,
                        start = MaterialTheme.spacing.medium,
                        end = MaterialTheme.spacing.medium
                    )
            ) {
                InfoTabs(
                    pagerState = pagerState,
                    backgroundColor = backgroundColor,
                    pages = pages,
                    pokemonInfo = pokemonInfo,
                )
            }
        }
        Image(
            painter = rememberImagePainter(data = pokemonInfo.imageUrl),
            contentDescription = null,
            modifier = imgModifier.graphicsLayer(translationY = 145f)
        )
    }
}