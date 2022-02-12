package com.soumikshah.futuramaquotes.model

import io.reactivex.Single
import retrofit2.http.GET

interface QuotesApi {
    @GET("soumikshah/MyFiles/main/futuramaquotes.json")
    fun getQuotes():Single<List<Quote>>
}