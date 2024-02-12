package com.example.dzhiadze.movies.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movies"
)

data class FavsDbEntity(
    @PrimaryKey val movieId: Int,
)