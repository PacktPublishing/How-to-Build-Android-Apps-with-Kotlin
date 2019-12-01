package com.android.testable.remote_media_provider.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DogEntity::class],
    version = 1
)
abstract class DogDatabase : RoomDatabase() {

    abstract fun dogDao(): DogDao
}