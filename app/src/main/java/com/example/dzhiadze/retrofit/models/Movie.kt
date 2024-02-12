package com.example.dzhiadze.retrofit.models

import com.example.dzhiadze.models.MovieModel

data class Movie(
    val filmId: Int,
    val nameRu: String?,
    val nameEn: String?,
    val genres: List<Genre>,
    val posterUrl: String,
    val posterUrlPreview: String,
    val year: Int
) {

    fun toMovieModel(): MovieModel = MovieModel(
        kinopoiskId = filmId,
        nameRu = nameRu ?: nameEn ?: "-",
        genres = genres,
        posterUrl = posterUrl,
        posterUrlPreview = posterUrlPreview,
        year = year
    )
}
