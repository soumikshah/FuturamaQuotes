package com.soumikshah.futuramaquotes.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SearchQuoteApi {
    @GET("api/characters/{name}")
    fun getSpecificQuotes(@Path("name")options:String):Single<List<Quote>>
}