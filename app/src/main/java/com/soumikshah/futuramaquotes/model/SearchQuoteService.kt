package com.soumikshah.futuramaquotes.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SearchQuoteService {
    private val SEARCH_URL = "http://futuramaapi.herokuapp.com"
    var api:SearchQuoteApi

    init {
        api = Retrofit.Builder()
            .baseUrl(SEARCH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SearchQuoteApi::class.java)
    }

    fun getSpecificQuotes(searchOptions:String):Single<List<Quote>>{
        return api.getSpecificQuotes(searchOptions)
    }
}