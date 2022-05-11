package com.jefisu.pokedexcompose.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.util.parseTypeToColor
import com.jefisu.pokedexcompose.ui.theme.spacing

@ExperimentalMaterialApi
@Composable
fun PokemonItem(
    pokemonInfo: Pokemon,
    modifier: Modifier = Modifier,
    boxShape: Shape = RoundedCornerShape(16.dp),
    boxColor: Color = parseTypeToColor(pokemonInfo.types.first()),
    backgroundImageSize: Dp = 150.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.small)
            .clip(boxShape)
            .background(boxColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = pokemonInfo.name.replaceFirstChar { it.titlecase() },
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            for (type in pokemonInfo.types) {
                ItemTypeStatAb(
                    text = type,
                    boxColor = LocalContentColor.current.copy(alpha = 0.2f)
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                if (pokemonInfo.types.size == 1) {
                    ItemTypeStatAb(
                        text = "",
                        boxColor = Color.Transparent
                    )
                }
            }
        }
        Box(
            contentAlignment = Alignment.Center
        ) {
            RotatingImageAnimation(
                image = R.drawable.ic_pokeball,
                size = backgroundImageSize
            )
            SvgImage(
                imgUrl = pokemonInfo.imageUrl,
                modifier = Modifier
                    .size(100.dp)
            )
        }
    }
}