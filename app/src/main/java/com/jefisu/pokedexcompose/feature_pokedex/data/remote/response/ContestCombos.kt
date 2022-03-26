package com.jefisu.pokedexcompose.feature_pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class ContestCombos(
    val normal: Normal,
    @SerializedName("super")
    val supe: Super
)