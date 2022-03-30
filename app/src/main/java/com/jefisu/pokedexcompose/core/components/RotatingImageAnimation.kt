package com.jefisu.pokedexcompose.core.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun RotatingImageAnimation(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    size: Dp,
    transparency: Float = 0.3f
) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotateAnim by infiniteTransition.animateFloat(
        initialValue = 10f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier = modifier
            .size(size)
            .alpha(transparency)
            .rotate(rotateAnim)
    )
}