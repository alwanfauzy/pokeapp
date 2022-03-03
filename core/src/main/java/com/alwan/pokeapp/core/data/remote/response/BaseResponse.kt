package com.alwan.pokeapp.core.data.remote.response

data class BaseResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<DetailResponse>?,
)
