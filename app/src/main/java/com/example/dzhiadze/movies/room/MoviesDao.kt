package com.example.dzhiadze.movies.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dzhiadze.movies.room.entities.FavsDbEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getAllNow() : List<FavsDbEntity>?

    @Insert
    fun insertMovie(movie: FavsDbEntity)

    @Delete
    fun deleteMovie(movie: FavsDbEntity)
}