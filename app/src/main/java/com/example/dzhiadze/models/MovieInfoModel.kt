package com.example.dzhiadze.models

import android.view.View
import com.example.dzhiadze.retrofit.models.Country
import com.example.dzhiadze.retrofit.models.Genre

data class MovieInfoModel(
    val nameRu: String,
    val description:String,
    val filmLength: Int,
    val genres: List<Genre>,
    val posterUrl: String,
    val year: Int,
    val countries: List<Country>
)
