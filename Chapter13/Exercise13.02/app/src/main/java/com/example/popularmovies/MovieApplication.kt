package com.example.popularmovies

import android.app.Application
import com.example.popularmovies.api.MovieService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieApplication : Application() {

    lateinit var movieService: MovieService

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        movieService = retrofit.create(MovieService::class.java)
    }
}