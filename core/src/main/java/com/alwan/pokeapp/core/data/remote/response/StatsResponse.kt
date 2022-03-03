package com.alwan.pokeapp.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class StatsResponse(
    @SerializedName("base_stat")
    val baseStat: Int?,
    val effort: Int?,
    val stat: DetailResponse?,
)
