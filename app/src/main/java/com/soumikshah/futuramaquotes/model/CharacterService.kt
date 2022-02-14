package com.soumikshah.futuramaquotes.model

import com.soumikshah.futuramaquotes.di.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CharacterService {

    @Inject
    lateinit var api:CharacterApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCharacters():Single<List<Character>>{
        return api.getCharacters()
    }
}