package com.example.popularmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.api.MovieService
import com.example.popularmovies.database.MovieDao
import com.example.popularmovies.database.MovieDatabase
import com.example.popularmovies.model.Movie

class MovieRepository(private val movieService: MovieService, private val movieDatabase: MovieDatabase) {
    private val apiKey = "your_api_key_here"

    private val movieLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    val movies: LiveData<List<Movie>>
        get() = movieLiveData

    suspend fun fetchMovies() {
        val movieDao: MovieDao = movieDatabase.movieDao()
        var moviesFetched = movieDao.getMovies()
        if (moviesFetched.isEmpty()) {
            try {
                val popularMovies = movieService.getPopularMovies(apiKey)
                moviesFetched = popularMovies.results
                movieDao.addMovies(moviesFetched)
            } catch (exception: Exception) {
                Log.d("MovieRepository", "Exception in fetchMovies: ${exception.message}")
            }
        }

        movieLiveData.postValue(moviesFetched)
    }

    suspend fun fetchMoviesFromNetwork() {
        val movieDao: MovieDao = movieDatabase.movieDao()
        try {
            val popularMovies = movieService.getPopularMovies(apiKey)
            val moviesFetched = popularMovies.results
            movieDao.addMovies(moviesFetched)
        } catch (exception: Exception) {
            Log.d("MovieRepository", "Exception in fetchMoviesFromNetwork: ${exception.message}")
        }
    }
}