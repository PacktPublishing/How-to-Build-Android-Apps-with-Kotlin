package com.example.tvguide

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.tvguide.database.TVDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TVShowWorker(private val context: Context, private val params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        val tvService = (context as TVApplication).tvService

        val tvShowRepository = TVShowRepository(tvService, TVDatabase.getInstance(applicationContext))
        CoroutineScope(Dispatchers.IO).launch {
            tvShowRepository.fetchTVShowsFromNetwork()
        }
        return Result.success()
    }
}