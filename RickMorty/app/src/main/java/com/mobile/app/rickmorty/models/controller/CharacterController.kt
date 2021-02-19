package com.mobile.app.rickmorty.models.controller

import com.google.gson.GsonBuilder
import com.mobile.app.rickmorty.models.interfaceApi.CharacterApi
import com.mobile.app.rickmorty.models.rickAndMorty.Characters
import com.mobile.app.rickmorty.models.rickAndMorty.ICharacters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterController : Callback<Characters> {

    val BASE_URL = "https://rickandmortyapi.com/api/"
    var characterListener: ICharacters? = null
    fun start(listener: ICharacters, id:String) {
        characterListener = listener
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val characterApi: CharacterApi = retrofit.create<CharacterApi>(CharacterApi::class.java)
        val call: Call<Characters> =
            characterApi.getCharacter(id)
        call.enqueue(this)
    }

    override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
        if (response.isSuccessful()) {
            val characters: Characters? = response.body()
            characterListener?.didReceiveCharacter(response.body())
        } else {
            println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<Characters>, t: Throwable) {
        t.printStackTrace()
    }
}