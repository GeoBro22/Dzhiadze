package com.example.dzhiadze.retrofit

import com.example.dzhiadze.retrofit.models.Movies
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitRepository {
    @Headers("Content-Type: application/json")
    @GET("/api/v2.2/films")
    suspend fun searchMovies(
        @Header("x-api-key") token: String,
        @Query("type") type: String
    ) : Movies

}