package com.example.dzhiadze.retrofit.models

data class Movie(
    val kinopoiskId: Int,
    val nameRu: String,
    val genres: List<Genre>,
    val posterUrl: String
)

