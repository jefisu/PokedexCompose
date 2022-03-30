package com.jefisu.pokedexcompose.core.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconButtonAnimation(
    selected: Boolean,
    icon: ImageVector,
    onClick: () -> Unit
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
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) Color.Red else MaterialTheme.colors.onSurface,
            modifier = Modifier.scale(scaleAnim.value)
        )
    }
}