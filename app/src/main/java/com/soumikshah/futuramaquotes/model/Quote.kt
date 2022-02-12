package com.soumikshah.futuramaquotes.model

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("quote")
    val dialog: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("character")
    val characterName: String?

)
