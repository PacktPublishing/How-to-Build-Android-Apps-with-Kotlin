package com.example.popularmovies

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.popularmovies.database.MovieDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieWorker(private val context: Context, private val params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        val movieService = (context as MovieApplication).movieService

        val movieRepository =
            MovieRepository(movieService, MovieDatabase.getInstance(applicationContext))
        CoroutineScope(Dispatchers.IO).launch {
            movieRepository.fetchMoviesFromNetwork()
        }
        return Result.success()
    }
}