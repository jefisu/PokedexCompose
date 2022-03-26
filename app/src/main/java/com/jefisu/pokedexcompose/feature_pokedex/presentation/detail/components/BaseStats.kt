package com.jefisu.pokedexcompose.feature_pokedex.presentation.detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.util.parseStatToAbbr
import com.jefisu.pokedexcompose.ui.theme.spacing

@Composable
fun BaseStats(
    modifier: Modifier = Modifier,
    pokemonInfo: Pokemon,
    animDelayPerItem: Int = 100,
    color: Color
) {
    val maxBaseStat = remember {
        pokemonInfo.stats.maxOf { it.baseStat }
    }
    Column(
        modifier = modifier
    ) {
        for (stat in pokemonInfo.stats) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = parseStatToAbbr(stat))
                StatItem(
                    statInitialValue = stat.baseStat,
                    statMaxValue = maxBaseStat,
                    statColor = color,
                    animDelay = 1 * animDelayPerItem
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }
    }
}