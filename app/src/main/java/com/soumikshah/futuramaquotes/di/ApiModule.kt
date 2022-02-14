package com.soumikshah.futuramaquotes.di

import com.soumikshah.futuramaquotes.model.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://raw.githubusercontent.com"
    private val SEARCH_URL = "http://futuramaapi.herokuapp.com"

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
    fun provideQuotesService():QuotesService{
        return QuotesService()
    }

    @Provides
    fun providesCharacterApi():CharacterApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CharacterApi::class.java)
    }

    @Provides
    fun providesCharacterService():CharacterService{
        return CharacterService()
    }

    @Provides
    fun providesSpecificQuotes():SearchQuoteApi{
        return Retrofit.Builder()
            .baseUrl(SEARCH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SearchQuoteApi::class.java)
    }

    @Provides
    fun provideSearchQuoteService():SearchQuoteService{
        return SearchQuoteService()
    }

}