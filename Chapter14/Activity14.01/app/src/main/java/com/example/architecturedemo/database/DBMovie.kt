package com.example.architecturedemo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class DBMovie(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "movie_title") val movieTitle: String,
    @ColumnInfo(name = "vote") val vote: Double
)