package com.soumikshah.futuramaquotes.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CharacterService {
    private val CHARACTER_URL = "https://raw.githubusercontent.com"
    var api:CharacterApi

    init {
        api = Retrofit.Builder()
            .baseUrl(CHARACTER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CharacterApi::class.java)
    }

    fun getCharacters():Single<List<Character>>{
        return api.getCharacters()
    }
}