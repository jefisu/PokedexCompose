package com.jefisu.pokedexcompose.core.components

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@ExperimentalAnimationApi
@Composable
fun Animation(
    enterTransition: EnterTransition,
    exitTransition: ExitTransition,
    content: @Composable () -> Unit,
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = visibleState,
        enter = enterTransition,
        exit = exitTransition
    ) {
        content()
    }
}