package com.example.newmovie2.models


import com.google.gson.annotations.SerializedName

data class PlotSummary(
    @SerializedName("author")
    val author: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("text")
    val text: String?
)