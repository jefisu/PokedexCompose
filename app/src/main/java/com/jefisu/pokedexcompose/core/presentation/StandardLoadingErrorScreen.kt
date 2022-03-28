package com.jefisu.pokedexcompose.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.core.components.LoadingAnimation
import com.jefisu.pokedexcompose.ui.theme.spacing

@Composable
fun StandardLoadingErrorScreen(
    hasError: Boolean,
    errorMessage: String,
    isLoading: Boolean = false,
    items: List<Any> = emptyList()
) {
    when {
        isLoading && items.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingAnimation()
            }
        }
        hasError -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(R.drawable.pikachu),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}