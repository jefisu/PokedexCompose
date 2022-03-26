package com.jefisu.pokedexcompose.feature_pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class Machine(
    val machine: MachineX,
    @SerializedName("version_group")
    val versionGroup: VersionGroupXX
)