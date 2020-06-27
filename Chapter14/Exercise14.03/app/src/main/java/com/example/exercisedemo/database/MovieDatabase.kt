package com.example.exercisedemo.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DBMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var db: MovieDatabase? = null
        fun getInstance(application: Application): MovieDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    application,
                    MovieDatabase::class.java, "database-movie"
                ).build()
            }
            return db!!
        }
    }
}