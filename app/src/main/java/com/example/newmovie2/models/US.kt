package com.example.newmovie2.models


import com.google.gson.annotations.SerializedName

data class US(
    @SerializedName("certificate")
    val certificate: String?,
    @SerializedName("country")
    val country: String?
)