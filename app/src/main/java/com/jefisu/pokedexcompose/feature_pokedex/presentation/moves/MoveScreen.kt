package com.jefisu.pokedexcompose.feature_pokedex.presentation.moves

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.core.components.ListAnimation
import com.jefisu.pokedexcompose.core.components.ListItem
import com.jefisu.pokedexcompose.core.components.TopBar
import com.jefisu.pokedexcompose.core.presentation.StandardLoadingErrorScreen
import com.jefisu.pokedexcompose.core.util.asString

@ExperimentalFoundationApi
@Composable
fun MoveScreen(
    navController: NavController,
    context: Context,
    viewModel: MoveViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = stringResource(R.string.moves),
            onClickIcon1 = navController::navigateUp
        )
        ListAnimation(
            items = state.moves,
            isLoading = state.isLoading
        ) { index, move ->
            val size = state.moves.size
            if (index >= size - 1 && !state.isLoading) {
                viewModel.getMoves()
            }
            ListItem(
                text = move.name,
                seconderyText = move.type,
                number = move.id,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
    StandardLoadingErrorScreen(
        hasError = state.hasError,
        errorMessage = state.errorMessage.asString(context),
        isLoading = state.isLoading,
        items = state.moves.toList()
    )
}