package com.jefisu.pokedexcompose.feature_pokedex.presentation.detail.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.core.components.ItemTypeStatAb
import com.jefisu.pokedexcompose.core.components.StartAnimation
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.util.Page
import com.jefisu.pokedexcompose.ui.theme.spacing
import kotlinx.coroutines.launch


@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun InfoTabs(
    modifier: Modifier = Modifier,
    pokemonInfo: Pokemon,
    pagerState: PagerState,
    backgroundColor: Color,
    pages: List<Page>
) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { /* Set empty indicator */ },
        divider = { /* Set empty divider */ },
        backgroundColor = Color.Transparent
    ) {
        pages.forEachIndexed { index, page ->
            ItemTypeStatAb(
                text = page.title,
                selected = index == pagerState.currentPage,
                boxColor = backgroundColor,
                modifier = modifier
                    .height(40.dp)
                    .clip(CircleShape)
                    .clickable {
                        scope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
            )
        }
    }
    HorizontalPager(
        count = pages.size,
        state = pagerState
    ) {
        val page = pages[pagerState.currentPage]
        val color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (page) {
                is Page.About -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            space = MaterialTheme.spacing.extraLarge,
                            alignment = Alignment.CenterHorizontally
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.spacing.small)
                    ) {
                        CharacteristicsAbout(
                            painter = R.drawable.ic_balance,
                            value = pokemonInfo.weight.toString(),
                            dataUnit = "Kg"
                        )
                        Divider(
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                                .height(50.dp)
                                .width(2.dp)
                        )
                        CharacteristicsAbout(
                            painter = R.drawable.ic_height,
                            value = pokemonInfo.height.toString(),
                            dataUnit = "m"
                        )
                    }
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                    Text(
                        text = stringResource(R.string.abilities),
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(pokemonInfo.abilities) { ability ->
                            ItemTypeStatAb(
                                text = ability,
                                boxColor = color,
                                boxWidth = 130.dp,
                                boxHeight = 40.dp,
                                boxShape = RoundedCornerShape(16.dp)
                            )
                        }
                    }
                }
                is Page.BaseStats -> {
                    StartAnimation(
                        enterTransition = slideInVertically(
                            animationSpec = tween(400)
                        ) + scaleIn() + fadeIn(),
                        exitTransition = slideOutVertically(
                            animationSpec = tween(400)
                        ) + scaleOut() + fadeOut()
                    ) {
                        BaseStats(
                            pokemonInfo = pokemonInfo,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                is Page.Moves -> {
                    StartAnimation(
                        enterTransition = slideInVertically(
                            animationSpec = tween(400)
                        ) + scaleIn() + fadeIn(),
                        exitTransition = slideOutVertically(
                            animationSpec = tween(400)
                        ) + scaleOut() + fadeOut()
                    ) {
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
                        ) {
                            val moves = if (pokemonInfo.moves.size >= 10) {
                                pokemonInfo.moves.subList(0, 10)
                            } else {
                                pokemonInfo.moves
                            }
                            items(moves) {
                                ItemTypeStatAb(
                                    text = it,
                                    boxHeight = 35.dp,
                                    boxColor = color
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}