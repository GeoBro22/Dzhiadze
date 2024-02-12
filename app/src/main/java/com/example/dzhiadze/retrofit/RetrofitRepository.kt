package com.example.dzhiadze.retrofit

import com.example.dzhiadze.retrofit.models.MovieInfo
import com.example.dzhiadze.retrofit.models.Movies
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitRepository {
    @Headers("Content-Type: application/json")
    @GET("/api/v2.2/films/{id}")
    suspend fun getMovieById(
        @Header("x-api-key") token: String,
        @Path("id") id: Int
    ) : MovieInfo

    @Headers("Content-Type: application/json")
    @GET("/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun searchMovies(
        @Header("x-api-key") token: String,
        @Query("page") page: Int
        //@Query("type") type: String
    ) : Movies

}