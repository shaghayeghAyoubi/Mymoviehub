package com.example.newmovie2.models


import com.google.gson.annotations.SerializedName

data class Ratings(
    @SerializedName("canRate")
    val canRate: Boolean?,
    @SerializedName("otherRanks")
    val otherRanks: List<OtherRank>?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("ratingCount")
    val ratingCount: Int?
)