package com.example.architecturedemo.top_rated

import android.app.Application
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.architecturedemo.database.MovieDatabase
import com.example.architecturedemo.ext.mapToDbMovie
import com.example.architecturedemo.network.MovieApi
import com.example.architecturedemo.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MovieWorker(private val appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        updateMovieCache(
            RetrofitClient.getRetrofitClient(),
            MovieDatabase.getInstance(appContext as Application)
        )
        return Result.success()
    }

    private fun updateMovieCache(retrofit: Retrofit, db: MovieDatabase) {
        CoroutineScope(Dispatchers.IO).launch {
            val movieApi = retrofit.create(MovieApi::class.java)
            try {
                val movieResponse =
                    movieApi.fetchTopRatedMovies("1d9a4704d81b27085f142914119d38fe")
                db.movieDao().addMovies(movieResponse.results.mapToDbMovie())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}