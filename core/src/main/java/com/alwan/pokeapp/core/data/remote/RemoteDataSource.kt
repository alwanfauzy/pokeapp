package com.alwan.pokeapp.core.data.remote

import android.annotation.SuppressLint
import android.util.Log
import com.alwan.pokeapp.core.data.remote.network.ApiResponse
import com.alwan.pokeapp.core.data.remote.network.ApiService
import com.alwan.pokeapp.core.data.remote.response.PokemonDetailResponse
import com.alwan.pokeapp.core.util.getUriLastPath
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    @SuppressLint("CheckResult")
    fun getListPokemon(): Flowable<ApiResponse<List<PokemonDetailResponse>>> {
        val result = PublishSubject.create<ApiResponse<List<PokemonDetailResponse>>>()
        val detailRequests = ArrayList<Flowable<PokemonDetailResponse>>()
        val detailResponses = ArrayList<PokemonDetailResponse>()
        val client = apiService.getListPokemon()

        client.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
            .take(1).subscribe({ response ->
                val pokemonResponse = response.results

                if (pokemonResponse != null) {
                    for (pokemon in pokemonResponse) {
                        val id = pokemon.url?.getUriLastPath()
                        detailRequests.add(apiService.getPokemon(id))
                    }
                }

                Flowable.zip(detailRequests) {
                    it.map { result -> result as PokemonDetailResponse }
                }.subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        detailResponses.addAll(data)
                        result.onNext(
                            if (detailResponses.isNotEmpty())
                                ApiResponse.Success(detailResponses.toList())
                            else
                                ApiResponse.Empty

                        )
                    }) { error ->
                        result.onNext(ApiResponse.Error(error.message.toString()))
                        Log.e(RemoteDataSource::class.java.simpleName, error.toString())
                    }

            }, { error ->
                result.onNext(ApiResponse.Error(error.message.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, error.toString())
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}