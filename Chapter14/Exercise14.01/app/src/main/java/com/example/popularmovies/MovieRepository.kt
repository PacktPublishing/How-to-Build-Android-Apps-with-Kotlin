package com.example.popularmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.api.MovieService
import com.example.popularmovies.model.Movie

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "your_api_key_here"

    private var movies: MutableLiveData<List<Movie>> = MutableLiveData()

    fun getMovies(): LiveData<List<Movie>> = movies

    suspend fun fetchMovies() {
        try {
            val popularMovies = movieService.getPopularMovies(apiKey)
            movies.postValue(popularMovies.results)
        } catch (exception: Exception) {
            Log.d("MovieRepository", "Exception in fetchMovies: ${exception.message}")
        }
    }
}