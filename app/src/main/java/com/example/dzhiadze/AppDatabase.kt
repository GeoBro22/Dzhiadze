package com.example.dzhiadze

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.dzhiadze.movies.room.MoviesDao
import com.example.dzhiadze.movies.room.entities.FavsDbEntity

@Database(
    version = 1,
    entities = [
        FavsDbEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}