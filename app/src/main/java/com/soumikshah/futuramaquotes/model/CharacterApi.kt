package com.soumikshah.futuramaquotes.model

import io.reactivex.Single
import retrofit2.http.GET

interface CharacterApi {
    @GET("soumikshah/MyFiles/main/allCharacterJSON.json")
    fun getCharacters(): Single<List<Character>>
}