package com.mobile.app.rickmorty.models.controller

import com.google.gson.GsonBuilder
import com.mobile.app.rickmorty.models.interfaceApi.EpisodeCountApi
import com.mobile.app.rickmorty.models.rickAndMorty.Info
import com.mobile.app.rickmorty.models.rickAndMorty.IEpisodeCount
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EpisodeCountController: Callback<Info> {

    val BASE_URL = "https://rickandmortyapi.com/api/"
    var episodeCountListener: IEpisodeCount? = null

    fun count(listener: IEpisodeCount) {
        episodeCountListener = listener
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val episodeCountApi: EpisodeCountApi = retrofit.create(EpisodeCountApi::class.java)
        val call: Call<Info> =
            episodeCountApi.getCount()
        call.enqueue(this)
    }

    override fun onResponse(call: Call<Info>, response: Response<Info>) {
        if (response.isSuccessful()) {
            val info: Info? = response.body()
            episodeCountListener?.didReceiveCount(response.body())
        } else {
            println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<Info>, t: Throwable) {
        t.printStackTrace()
    }
}