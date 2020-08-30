package com.example.tvguide

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class TVApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest
            .Builder(TVShowWorker::class.java, 24, TimeUnit.HOURS)
            .setConstraints(constraints)
            .addTag("tvshow-work")
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}