package com.jefisu.pokedexcompose.feature_pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class Normal(
    @SerializedName("use_after")
    val useAfter: List<UseAfter>,
    @SerializedName("use_before")
    val useBefore: Any
)