package com.jefisu.pokedexcompose.core.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder

@Composable
fun SvgImage(
    modifier: Modifier = Modifier,
    imgUrl: String
) {
    val context = LocalContext.current
    Image(
        painter = rememberAsyncImagePainter(
            model = imgUrl,
            imageLoader = ImageLoader.Builder(context)
                .components {
                    add(SvgDecoder.Factory())
                }
                .build()
        ),
        contentDescription = "Pokemon image",
        modifier = modifier
    )
}