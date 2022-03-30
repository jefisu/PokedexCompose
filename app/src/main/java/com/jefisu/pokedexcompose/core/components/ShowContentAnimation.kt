package com.jefisu.pokedexcompose.core.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShowContentAnimation(
    isVisible: Boolean,
    durationAnim: Int = 500,
    space: Dp = 12.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(animationSpec = tween(durationAnim)) + fadeIn(),
        exit = shrinkVertically(animationSpec = tween(durationAnim)) + fadeOut(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(space)
        ) {
            content()
        }
    }
}