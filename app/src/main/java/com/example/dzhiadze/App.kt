package com.example.dzhiadze

import android.app.Application
import androidx.room.Room
import com.example.dzhiadze.movies.room.RoomFavesRepository


class App : Application() {

    companion object{
        const val FROM="Faves"
    }
    lateinit var service: Service
    private lateinit var db: AppDatabase
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "Faves.db"
        )
            .createFromAsset("favesMov.db")//.fallbackToDestructiveMigration()
            .build()
        val repositoryFaves = RoomFavesRepository(db.getMoviesDao())
        service = Service(repositoryFaves)
    }

}