package com.jefisu.pokedexcompose.core.util

import androidx.annotation.StringRes
import com.jefisu.pokedexcompose.R

sealed class UiText {
    data class StringResource(@StringRes val id: Int) : UiText()

    companion object {
        fun unknownError(): UiText {
            return UiText.StringResource(R.string.unknown_error)
        }
    }
}