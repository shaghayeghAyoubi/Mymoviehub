package com.example.newmovie2.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("runningTimeInMinutes")
    val runningTimeInMinutes: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("titleType")
    val titleType: String,
    @SerializedName("year")
    val year: Int
)