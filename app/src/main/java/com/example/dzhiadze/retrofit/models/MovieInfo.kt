package com.example.dzhiadze.retrofit.models

import com.example.dzhiadze.models.MovieInfoModel
import com.example.dzhiadze.models.MovieModel

data class MovieInfo(
    val nameRu: String?,
    val nameEn: String?,
    val genres: List<Genre>,
    val description:String?,
    val filmLength: Int?,
    val posterUrl: String,
    val year: Int?,
    val countries: List<Country>
) {

    fun toMovieInfoModel(): MovieInfoModel = MovieInfoModel(
        nameRu = nameRu ?: nameEn ?: "Неизвестно",
        genres = genres,
        description = description?: "Неизвестно",
        filmLength = filmLength?: 0,
        posterUrl = posterUrl,
        year = year?: 0,
        countries = countries
    )
}

