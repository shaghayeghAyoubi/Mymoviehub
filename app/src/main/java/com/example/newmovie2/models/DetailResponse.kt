package com.example.newmovie2.models


import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("plotSummary")
    val plotSummary: PlotSummary?,
    @SerializedName("ratings")
    val ratings: Ratings?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("title")
    val title: Result?
)