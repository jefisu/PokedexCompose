package com.jefisu.pokedexcompose.feature_pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class Meta(
    val ailment: Ailment,
    @SerializedName("ailment_chance")
    val ailmentChance: Int,
    val category: Category,
    @SerializedName("crit_rate")
    val critRate: Int,
    val drain: Int,
    @SerializedName("flinch_chance")
    val flinchChance: Int,
    val healing: Int,
    @SerializedName("maxHits")
    val max_hits: Any,
    @SerializedName("max_turns")
    val max_turns: Any,
    @SerializedName("min_hits")
    val minHits: Any,
    @SerializedName("min_turns")
    val minTurns: Any,
    @SerializedName("stat_chance")
    val statChance: Int
)