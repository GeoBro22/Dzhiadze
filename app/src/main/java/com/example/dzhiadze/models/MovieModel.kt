package com.example.dzhiadze.models

import android.view.View.INVISIBLE
import com.example.dzhiadze.retrofit.models.Genre

data class MovieModel(
    val kinopoiskId: Int,
    val nameRu: String,
    val genres: List<Genre>,
    val posterUrl: String,
    val posterUrlPreview: String,
    val year: Int,
    var isFavs: Int =INVISIBLE
)


