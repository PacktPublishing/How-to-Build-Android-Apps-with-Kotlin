package com.example.popularmovies

import androidx.lifecycle.*
import com.example.popularmovies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

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

    class Factory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(movieRepository) as T
            }
            throw IllegalArgumentException("Unable to create ViewModel")
        }
    }
}