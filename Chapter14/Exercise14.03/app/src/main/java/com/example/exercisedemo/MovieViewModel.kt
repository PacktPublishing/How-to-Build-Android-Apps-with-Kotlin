package com.example.exercisedemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exercisedemo.database.MovieDatabase
import com.example.exercisedemo.ext.mapToDbMovie
import com.example.exercisedemo.ext.mapToMovie
import com.example.exercisedemo.model.Movie
import com.example.exercisedemo.network.MovieApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MovieViewModel(
    private val application: Application,
    private val retrofit: Retrofit,
    private val db: MovieDatabase
) : ViewModel() {
    private val movieResponseLiveData = MutableLiveData<List<Movie>>()

    fun getMovies(): MutableLiveData<List<Movie>> {
        return movieResponseLiveData
    }

    fun fetchMovies() {
        CoroutineScope(IO).launch {
            //If movies available in cache the return from cache
            val cachedMovies = db.movieDao().getAll()
            if (cachedMovies.isNotEmpty()) {
                Log.d("Movie logs", "Movies available in cache")
                movieResponseLiveData.postValue(cachedMovies.mapToMovie())
            } else {
                //If movies not available in cache the fetch from the API, Update the cache and then return the result
                Log.d("Movie logs", "Movies fetching from API")
                val movieApi = retrofit.create(MovieApi::class.java)
                try {
                    val movieResponse =
                        movieApi.fetchTopRatedMovies("1d9a4704d81b27085f142914119d38fe")
                    db.movieDao().addMovies(movieResponse.results.mapToDbMovie())
                    movieResponseLiveData.postValue(movieResponse.results)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    class Factory(
        private val application: Application,
        private val retrofit: Retrofit,
        private val db: MovieDatabase
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(application, retrofit, db) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}