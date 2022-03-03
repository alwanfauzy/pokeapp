package com.alwan.pokeapp.core.data.remote.response

data class PokemonDetailResponse(
    val id: Int?,
    val name: String?,
    val weight: Int?,
    val height: Int?,
    val stats: List<StatsResponse>?,
    val abilities: List<AbilitiesResponse>?,
    val sprites: SpritesResponse?,
)
