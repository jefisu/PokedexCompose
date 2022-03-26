package com.jefisu.pokedexcompose.feature_pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class MoveResponse(
    val accuracy: Int,
    @SerializedName("contest_combos")
    val contestCombos: ContestCombos,
    @SerializedName("contest_effect")
    val contestEffect: ContestEffect,
    @SerializedName("contest_type")
    val contestType: ContestType,
    @SerializedName("damage_class")
    val damageClass: DamageClass,
    @SerializedName("effect_chance")
    val effectChance: Any,
    @SerializedName("effect_changes")
    val effectChanges: List<Any>,
    @SerializedName("effect_entries")
    val effectEntries: List<EffectEntry>,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    val generation: Generation,
    val id: Int,
    @SerializedName("learned_by_pokemon")
    val learnedByPokemon: List<LearnedByPokemon>,
    val machines: List<Machine>,
    val meta: Meta,
    val name: String,
    val names: List<Name>,
    @SerializedName("past_values")
    val pastValues: List<Any>,
    val power: Int,
    val pp: Int,
    val priority: Int,
    @SerializedName("stat_changes")
    val statChanges: List<Any>,
    @SerializedName("super_contest_effect")
    val superContestEffect: SuperContestEffect,
    val target: Target,
    val type: TypeXX
)