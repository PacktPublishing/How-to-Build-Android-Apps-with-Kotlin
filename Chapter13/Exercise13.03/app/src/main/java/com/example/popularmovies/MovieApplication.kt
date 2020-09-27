package com.example.popularmovies

import android.app.Application
import com.example.popularmovies.api.MovieService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieApplication : Application() {

    lateinit var movieRepository: MovieRepository
    lateinit var movieService: MovieService

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        movieService = retrofit.create(MovieService::class.java)

        movieRepository = MovieRepository(movieService)
    }
}