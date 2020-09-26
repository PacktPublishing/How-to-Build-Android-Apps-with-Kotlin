package com.example.tvguide

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.tvguide.api.TelevisionService
import com.example.tvguide.database.TVDatabase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

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

        tvShowRepository = TVShowRepository(tvService, TVDatabase.getInstance(applicationContext))

        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest
            .Builder(TVShowWorker::class.java, 1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .addTag("tvshow-work")
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}