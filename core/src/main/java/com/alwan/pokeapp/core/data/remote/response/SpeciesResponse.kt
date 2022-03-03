package com.alwan.pokeapp.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class SpeciesResponse(
    @SerializedName("evolution_chain")
    val evolutionChain: UrlResponse?,
)