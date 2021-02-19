package com.mobile.app.rickmorty.models.interfaceApi

import com.mobile.app.rickmorty.models.rickAndMorty.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {

    @GET("character/{id}")
    fun getCharacter(@Path("id") id:String): Call<Characters>
}