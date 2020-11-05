package com.android.testable.roomexample

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.testable.roomexample.Article

@Database(
    entities = [Article::class, Journalist::class, JoinedArticleJournalist::class],
    version = 1
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun journalistDao(): JournalistDao

    abstract fun joinedArticleJournalistDao(): JoinedArticleJournalistDao
}