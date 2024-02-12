package com.example.dzhiadze.movies.room


import com.example.dzhiadze.movies.MoviesRepository
import com.example.dzhiadze.movies.room.entities.FavsDbEntity

class RoomFavesRepository(private val moviesDao:MoviesDao): MoviesRepository {

    override suspend fun getFavesId(): List<FavsDbEntity> {
        return moviesDao.getAllNow()?: ArrayList<FavsDbEntity>()
    }

    override suspend fun insertFavesId(favsDbEntity: FavsDbEntity){
        moviesDao.insertMovie(favsDbEntity)
    }

    override suspend fun deleteFavesId(favsDbEntity: FavsDbEntity) {
        moviesDao.deleteMovie(favsDbEntity)
    }
}