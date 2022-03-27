package com.jefisu.pokedexcompose.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.pokedexcompose.feature_pokedex.util.parseTypeToColor
import com.jefisu.pokedexcompose.feature_pokedex.util.parseTypeToImage
import com.jefisu.pokedexcompose.ui.theme.spacing

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    text: String,
    textFontSize: TextUnit = 22.sp,
    seconderyText: String = "",
    number: Int? = null,
    shape: Shape = RoundedCornerShape(16.dp),
    imageSize: Dp = 50.dp
) {
    val value = seconderyText.ifEmpty { text }
    Box(
        modifier = modifier
            .clip(shape)
            .background(parseTypeToColor(value).copy(alpha = 0.6f))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (number != null) Arrangement.SpaceBetween else Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(parseTypeToImage(value)),
                    contentDescription = null,
                    modifier = Modifier.size(imageSize),
                    colorFilter = ColorFilter.tint(parseTypeToColor(value))
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                Text(text = text.replaceFirstChar { it.titlecase() }, fontSize = textFontSize)
            }
            if (number != null) {
                Text(text = "#$number", fontSize = MaterialTheme.typography.subtitle1.fontSize)
            }
        }
    }
}