package com.example.popularmovies

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest
            .Builder(MovieWorker::class.java, 24, TimeUnit.HOURS)
            .setConstraints(constraints)
            .addTag("movie-work")
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}