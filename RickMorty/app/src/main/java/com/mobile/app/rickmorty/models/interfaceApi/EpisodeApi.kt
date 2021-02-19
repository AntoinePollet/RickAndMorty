package com.mobile.app.rickmorty.models.interfaceApi

import com.mobile.app.rickmorty.models.rickAndMorty.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {
    @GET("episode/{id}")
    fun getEpisode(@Path("id") id: String): Call<Episode>

    @GET("episode")
    fun getCount(): Call<Episode>

}