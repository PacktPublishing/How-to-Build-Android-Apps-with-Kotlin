package com.example.tvguide.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tvguide.model.TVShow

@Dao
interface TVDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTVShows(tvShows: List<TVShow>)

    @Query("SELECT * FROM tvshows")
    fun getTVShows(): List<TVShow>
}