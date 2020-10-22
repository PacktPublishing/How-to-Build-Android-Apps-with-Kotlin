package com.example.popularmovies

import android.app.Application
import com.example.popularmovies.api.MovieService
import com.example.popularmovies.database.MovieDatabase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieApplication : Application() {

    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val movieService = retrofit.create(MovieService::class.java)

        val movieDatabase = MovieDatabase.getInstance(applicationContext)

        movieRepository = MovieRepository(movieService, movieDatabase)
    }
}