package com.alwan.pokeapp.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ChainResponse(
    @SerializedName("evolves_to")
    val evolvesTo: List<ChainResponse>?,
    val species: DetailResponse,
)
