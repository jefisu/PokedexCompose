package com.jefisu.pokedexcompose.feature_pokedex.presentation.types

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.core.components.ListItem
import com.jefisu.pokedexcompose.core.components.TopBar
import com.jefisu.pokedexcompose.core.presentation.StandardLoadingErrorScreen
import com.jefisu.pokedexcompose.core.util.asString
import com.jefisu.pokedexcompose.ui.theme.spacing

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun TypeScreen(
    navController: NavController,
    context: Context,
    viewModel: TypeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(
            title = stringResource(R.string.types),
            onClickIcon1 = navController::navigateUp
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearEasing
                    )
                )
        ) {
            items(state.types) { type ->
                ListItem(
                    text = type.name,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
    StandardLoadingErrorScreen(
        hasError = state.hasError,
        errorMessage = state.errorMessage.asString(context),
        isLoading = state.isLoading,
        items = state.types
    )
}