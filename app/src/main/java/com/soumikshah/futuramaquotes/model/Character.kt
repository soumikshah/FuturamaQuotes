package com.soumikshah.futuramaquotes.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("ScreenName")
    val screenName:String?,

    @SerializedName("picUrl")
    val characterImage:String?
)
