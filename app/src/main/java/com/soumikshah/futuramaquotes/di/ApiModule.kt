package com.soumikshah.futuramaquotes.di

import com.soumikshah.futuramaquotes.model.QuotesApi
import com.soumikshah.futuramaquotes.model.QuotesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://raw.githubusercontent.com"

    @Provides
    fun provideQuotesApi():QuotesApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(QuotesApi::class.java)
    }

    @Provides
    fun provideCountriesService():QuotesService{
        return QuotesService()
    }
}