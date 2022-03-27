package com.jefisu.pokedexcompose.feature_pokedex.presentation.moves

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.core.components.ListItem
import com.jefisu.pokedexcompose.core.components.TopBar
import com.jefisu.pokedexcompose.core.presentation.StandardLoadingErrorScreen
import com.jefisu.pokedexcompose.core.util.asString
import com.jefisu.pokedexcompose.ui.theme.spacing

@ExperimentalFoundationApi
@Composable
fun MoveScreen(
    navController: NavController,
    context: Context,
    viewModel: MoveViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.extraSmall)
    ) {
        TopBar(
            title = stringResource(R.string.moves),
            onClickIcon1 = navController::navigateUp
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
        LazyColumn(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)) {
            state.moves.forEach { (type, moves) ->
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "${type.replaceFirstChar { it.titlecase() }} Type",
                            fontSize = 22.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(moves) { move ->
                    ListItem(
                        text = move.name,
                        seconderyText = type,
                        number = move.id,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
    StandardLoadingErrorScreen(
        hasError = state.hasError,
        errorMessage = state.errorMessage.asString(context),
        isLoading = state.isLoading,
        items = state.moves.toList()
    )
}