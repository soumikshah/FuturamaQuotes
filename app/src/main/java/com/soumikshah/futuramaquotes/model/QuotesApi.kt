package com.soumikshah.futuramaquotes.model

import io.reactivex.Single
import retrofit2.http.GET

interface QuotesApi {
    @GET("api/quotes")
    fun getQuotes():Single<List<Quote>>
}