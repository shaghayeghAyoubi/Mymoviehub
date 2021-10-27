package com.example.newmovie2.models


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("results")
    val results: List<Result>
)