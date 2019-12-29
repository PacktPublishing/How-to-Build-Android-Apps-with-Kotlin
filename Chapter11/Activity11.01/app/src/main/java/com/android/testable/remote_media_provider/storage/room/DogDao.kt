package com.android.testable.remote_media_provider.storage.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDogs(dogs: List<DogEntity>)

    @Query("SELECT * FROM dogs")
    fun loadDogs(): LiveData<List<DogEntity>>

    @Query("DELETE FROM dogs")
    fun deleteAll()
}