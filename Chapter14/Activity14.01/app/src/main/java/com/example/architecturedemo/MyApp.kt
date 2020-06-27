package com.example.topratedmoviewitharchitecturepattern

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.architecturedemo.top_rated.MovieWorker
import java.util.concurrent.TimeUnit

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initWorkManager()
    }

    private fun initWorkManager() {
        val networkConstraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val myWorkRequest = PeriodicWorkRequest
            .Builder(MovieWorker::class.java, 24, TimeUnit.HOURS)
            .setConstraints(networkConstraints)
            .addTag("movie_work")
            .build()
        WorkManager.getInstance(applicationContext).enqueue(myWorkRequest)
    }
}