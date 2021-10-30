package com.example.newmovie2.models


import com.google.gson.annotations.SerializedName

data class OtherRank(
    @SerializedName("id")
    val id: String?,
    @SerializedName("label")
    val label: String?,
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("rankType")
    val rankType: String?
)