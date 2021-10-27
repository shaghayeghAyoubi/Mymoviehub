package com.example.newmovie2.models


import com.google.gson.annotations.SerializedName

data class Summary(
    @SerializedName("category")
    val category: String,
    @SerializedName("characters")
    val characters: List<String>,
    @SerializedName("displayYear")
    val displayYear: String
)