package com.soumikshah.futuramaquotes.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class QuotesService {
    private val BASE_URL = "http://futuramaapi.herokuapp.com"
    var api:QuotesApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(QuotesApi::class.java)
    }

    fun getQuotes(): Single<List<Quote>>{
        return api.getQuotes()
    }

}