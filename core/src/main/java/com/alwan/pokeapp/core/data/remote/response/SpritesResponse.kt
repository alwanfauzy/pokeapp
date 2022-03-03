package com.alwan.pokeapp.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class SpritesResponse(
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("front_default")
    val frontDefault: String?,
)
