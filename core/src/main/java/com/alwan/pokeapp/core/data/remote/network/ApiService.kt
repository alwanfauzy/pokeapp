package com.alwan.pokeapp.core.data.remote.network

import com.alwan.pokeapp.core.data.remote.response.PokemonDetailResponse
import com.alwan.pokeapp.core.data.remote.response.BaseResponse
import com.alwan.pokeapp.core.data.remote.response.EvolutionResponse
import com.alwan.pokeapp.core.data.remote.response.SpeciesResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon")
    fun getListPokemon(): Flowable<BaseResponse>

    @GET("pokemon/{id}")
    fun getPokemon(
        @Path("id") id: String?,
    ): Flowable<PokemonDetailResponse>

    @GET("pokemon-species/{name}")
    fun getPokemonSpecies(
        @Path("name") name: String?,
    ): Flowable<SpeciesResponse>

    @GET("evolution-chain/{id}")
    fun getEvolutionChain(
        @Path("id") id: String?,
    ): Flowable<EvolutionResponse>
}