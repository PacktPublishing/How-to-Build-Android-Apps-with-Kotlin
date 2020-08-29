package com.example.popularmovies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.popularmovies.database.MovieDatabase
import com.example.popularmovies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository: MovieRepository by lazy {
        MovieRepository(MovieDatabase.getInstance(application.applicationContext))
    }

    private var movies: LiveData<List<Movie>> = movieRepository.getMovies()

    val popularMovies: LiveData<List<Movie>>
        get() = movies.map { list ->
            list.filter {
                it.release_date.startsWith(
                    Calendar.getInstance().get(Calendar.YEAR).toString()
                )
            }.sortedBy { it.title }
        }

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.fetchMovies()
        }
    }
}