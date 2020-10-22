package com.example.tvguide

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.tvguide.database.TVDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TVShowWorker(private val context: Context, params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        val tvShowRepository = (context as TVApplication).tvShowRepository
        CoroutineScope(Dispatchers.IO).launch {
            tvShowRepository.fetchTVShowsFromNetwork()
        }
        return Result.success()
    }
}