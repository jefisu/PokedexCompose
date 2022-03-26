package com.jefisu.pokedexcompose.feature_pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class Super(
    @SerializedName("use_after")
    val useAfter: List<Any>,
    @SerializedName("use_before")
    val useBefore: Any
)