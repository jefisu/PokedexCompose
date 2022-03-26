package com.jefisu.pokedexcompose.core.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun ClickAnimation(
    selected: Boolean,
    content: @Composable (Float) -> Unit
) {
    val scaleAnim = remember { Animatable(1f) }
    LaunchedEffect(key1 = selected) {
        if (selected) {
            scaleAnim.animateTo(
                targetValue = 0.3f,
                animationSpec = tween(50)
            )
            scaleAnim.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }
    }
    content(scaleAnim.value)
}