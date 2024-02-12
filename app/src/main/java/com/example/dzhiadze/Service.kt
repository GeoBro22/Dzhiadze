package com.example.dzhiadze

import com.example.dzhiadze.movies.MoviesRepository
import com.example.dzhiadze.movies.room.RoomFavesRepository
import com.example.dzhiadze.movies.room.entities.FavsDbEntity

class Service(private val favesRepository: RoomFavesRepository) {
    suspend fun getFavsMoviesId(): MutableList<FavsDbEntity> {
        return favesRepository.getFavesId().toMutableList()
    }
    suspend fun insertFavsMoviesId(entity: FavsDbEntity){
        favesRepository.insertFavesId(entity)
    }
    suspend fun deleteFavsMoviesId(entity: FavsDbEntity){
        favesRepository.deleteFavesId(entity)
    }
}