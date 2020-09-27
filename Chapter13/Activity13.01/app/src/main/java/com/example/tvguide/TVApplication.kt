package com.example.tvguide

import android.app.Application
import com.example.tvguide.api.TelevisionService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TVApplication : Application() {

    lateinit var tvService: TelevisionService
    lateinit var tvShowRepository: TVShowRepository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        tvService = retrofit.create(TelevisionService::class.java)

        tvShowRepository = TVShowRepository(tvService)
    }
}