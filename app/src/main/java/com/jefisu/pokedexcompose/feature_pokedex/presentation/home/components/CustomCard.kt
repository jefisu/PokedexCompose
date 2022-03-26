package com.jefisu.pokedexcompose.feature_pokedex.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.pokedexcompose.ui.theme.LightRed
import com.jefisu.pokedexcompose.ui.theme.spacing

@ExperimentalMaterialApi
@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    imgModifier: Modifier = Modifier,
    text: String,
    img: Int,
    textStyle: TextStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
    backgroundColor: Color = LightRed,
    shape: Shape = RoundedCornerShape(10.dp),
    imageSize: Dp = 80.dp,
    imageAlpha: Float = 0.2f,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor.copy(alpha = 0.9f))
            .clickable { onClick() },
        contentAlignment = Alignment.BottomStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = text,
                style = textStyle
            )
        }
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = null,
                modifier = imgModifier
                    .size(imageSize)
                    .alpha(imageAlpha),
                contentScale = ContentScale.Crop
            )
        }
    }
}