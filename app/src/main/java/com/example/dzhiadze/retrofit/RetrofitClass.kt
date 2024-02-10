package com.example.dzhiadze.retrofit

import android.util.Log
import com.example.dzhiadze.retrofit.models.Movies

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClass {
    val token = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    val apiService = Retrofit.Builder()
        .baseUrl("https://kinopoiskapiunofficial.tech")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RetrofitRepository::class.java)
    suspend fun getMoviesFromApi():Movies {

        val response = apiService.searchMovies(
            token = token,
            type = "FILM"
        )
        Log.d("AAA", response.toString())

        return response
    }
}