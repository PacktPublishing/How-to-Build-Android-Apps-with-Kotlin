package com.android.testable.repository

import android.app.Application
import androidx.room.Room
import com.android.testable.repository.api.PostService
import com.android.testable.repository.db.PostDatabase
import com.android.testable.repository.repository.PostMapper
import com.android.testable.repository.repository.PostRepository
import com.android.testable.repository.repository.PostRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


class RepositoryApplication : Application() {

    lateinit var postRepository: PostRepository

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val postService = retrofit.create<PostService>(PostService::class.java)

        val notesDatabase =
            Room.databaseBuilder(applicationContext, PostDatabase::class.java, "post-db")
                .build()

        postRepository = PostRepositoryImpl(
            postService,
            notesDatabase.postDao(),
            PostMapper(),
            Executors.newSingleThreadExecutor()
        )
    }
}