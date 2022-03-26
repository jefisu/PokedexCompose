package com.jefisu.pokedexcompose.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.jefisu.pokedexcompose.ui.theme.spacing

@Composable
fun TopBar(
    icon1: ImageVector = Icons.Default.ArrowBack,
    icon2: ImageVector? = null,
    title: String = "",
    selected: Boolean = false,
    onClickIcon1: () -> Unit = {},
    onClickIcon2: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (icon2 != null) Arrangement.SpaceBetween else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onClickIcon1) {
                Icon(imageVector = icon1, contentDescription = null)
            }
            if (title.isNotBlank()) {
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        if (icon2 != null) {
            ClickAnimation(selected = selected) {
                IconButton(onClick = onClickIcon2) {
                    Icon(
                        imageVector = icon2,
                        contentDescription = null,
                        tint = if (selected) Color.Red else MaterialTheme.colors.onSurface,
                        modifier = Modifier.scale(it)
                    )
                }
            }
        }
    }
}