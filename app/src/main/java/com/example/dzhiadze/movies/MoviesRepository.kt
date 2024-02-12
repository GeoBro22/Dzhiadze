package com.example.dzhiadze.movies

import com.example.dzhiadze.movies.room.entities.FavsDbEntity

interface MoviesRepository {
    suspend fun getFavesId(): List<FavsDbEntity>
    suspend fun insertFavesId(favsDbEntity: FavsDbEntity)
    suspend fun deleteFavesId(favsDbEntity: FavsDbEntity)
}