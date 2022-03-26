package com.jefisu.pokedexcompose.feature_pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class EffectEntry(
    val effect: String,
    val language: Language,
    @SerializedName("short_effect")
    val shortEffect: String
)