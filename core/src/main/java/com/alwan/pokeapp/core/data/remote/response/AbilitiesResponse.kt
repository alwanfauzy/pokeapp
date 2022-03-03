package com.alwan.pokeapp.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class AbilitiesResponse(
    val ability: DetailResponse?,
    @SerializedName("is_hidden")
    val isHidden: Boolean?,
    val slot: Int?,
)
