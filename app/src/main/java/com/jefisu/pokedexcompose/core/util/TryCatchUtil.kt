package com.jefisu.pokedexcompose.core.util

import com.jefisu.pokedexcompose.R
import okio.IOException
import retrofit2.HttpException

suspend fun <T>httpRequest(content: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(
            data = content()
        )
    } catch (e: IOException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
        )
    } catch (e: HttpException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }
}