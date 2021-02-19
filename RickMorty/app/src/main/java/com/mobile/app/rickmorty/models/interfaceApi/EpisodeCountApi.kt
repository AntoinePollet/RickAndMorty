package com.mobile.app.rickmorty.models.interfaceApi

import com.mobile.app.rickmorty.models.rickAndMorty.Info

import retrofit2.Call
import retrofit2.http.GET

interface EpisodeCountApi {
    @GET("episode")
    fun getCount(): Call<Info>
}
