package com.example.newmovie2.apiservices

import com.example.newmovie2.models.Response
import com.example.newmovie2.models.Result
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URI = "https://imdb8.p.rapidapi.com/"

private var retrofit = retrofit2.Retrofit.Builder()
    .baseUrl(BASE_URI)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface MovieApiService {
    @Headers(
        "Accept: application/json",
        "x-rapidapi-host': 'imdb8.p.rapidapi.com",
        "x-rapidapi-key': '3756b5f303mshab9d47fafa9b6abp1e0e53jsnb03b1553b9e1"
    )
    @GET("find/title")
    suspend fun getTitle(@Query("q") search: String): Response

    @Headers(
        "Accept: application/json",
        "x-rapidapi-host': 'imdb8.p.rapidapi.com",
        "x-rapidapi-key': '3756b5f303mshab9d47fafa9b6abp1e0e53jsnb03b1553b9e1"
    )
    @GET("title/get-details")
    suspend fun getDetail(@Query("tconst") detail: String): Result
}
/**
 * A public Api object that exposes the lazy-initilaized Retrofit service
 */

object MovieApi {
    val retrofitService: MovieApiService by lazy { retrofit.create(MovieApiService::class.java)}
}

