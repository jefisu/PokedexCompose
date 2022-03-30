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
import androidx.compose.material.*
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
import com.jefisu.pokedexcompose.core.components.StartAnimation
import com.jefisu.pokedexcompose.core.components.ItemTypeStatAb
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (page) {
                is Page.About -> {
                    Row {
                        CharacteristicsAbout(
                            painter = R.drawable.ic_balance,
                            text = pokemonInfo.weight.toString(),
                            dataUnit = "Kg"
                        )
                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraLarge))
                        CharacteristicsAbout(
                            painter = R.drawable.ic_height,
                            text = pokemonInfo.height.toString(),
                            dataUnit = "m",
                        )
                    }
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                    Text(
                        text = stringResource(R.string.abilities),
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        for (ability in pokemonInfo.abilities) {
                            ItemTypeStatAb(
                                text = ability,
                                boxColor = LocalContentColor.current.copy(ContentAlpha.disabled),
                                boxWidth = 100.dp
                            )
                            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
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
                            color = backgroundColor,
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
                        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
                            items(pokemonInfo.moves) {
                                ItemTypeStatAb(
                                    text = it,
                                    boxHeight = 35.dp,
                                    modifier = Modifier.padding(bottom = 12.dp, end = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}