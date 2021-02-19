package com.mobile.app.rickmorty.models.controller

import com.google.gson.GsonBuilder
import com.mobile.app.rickmorty.models.interfaceApi.EpisodeApi
import com.mobile.app.rickmorty.models.rickAndMorty.Episode
import com.mobile.app.rickmorty.models.rickAndMorty.IEpisode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EpisodeController : Callback<Episode> {

    val BASE_URL = "https://rickandmortyapi.com/api/"
    var rickMortyListener: IEpisode? = null
    fun start(listener: IEpisode, id:String) {
        rickMortyListener = listener
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val episodeApi: EpisodeApi = retrofit.create<EpisodeApi>(EpisodeApi::class.java)
        val call: Call<Episode> =
            episodeApi.getEpisode(id)
        call.enqueue(this)
    }

    override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
        if (response.isSuccessful()) {
            val episode: Episode? = response.body()
            rickMortyListener?.didReceiveData(response.body())
        } else {
            println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<Episode>, t: Throwable) {
        t.printStackTrace()
    }
}