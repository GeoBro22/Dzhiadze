package com.example.dzhiadze.retrofit

import com.example.dzhiadze.models.MovieInfoModel
import com.example.dzhiadze.models.MovieModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClass {
    val token = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    val apiService = Retrofit.Builder()
        .baseUrl("https://kinopoiskapiunofficial.tech")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RetrofitRepository::class.java)
    suspend fun getMovieByIdFromApi(id:Int):MovieInfoModel{
        val response = apiService.getMovieById(
            token = token,
            id = id
        )
        return response.toMovieInfoModel()
    }

    suspend fun getMoviesFromApi(page :Int):List<MovieModel> {

        val response = apiService.searchMovies(
            token = token,
            page = page
        )

        val movies: MutableList<MovieModel> = mutableListOf()
        for (i in 0 until response.films.size) {
            val movie = response.films[i].toMovieModel()
            movies.add(movie)
        }
        return movies
    }
}