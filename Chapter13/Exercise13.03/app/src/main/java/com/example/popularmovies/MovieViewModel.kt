package com.example.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularmovies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    init {
        fetchPopularMovies()
    }

    fun getPopularMovies(): LiveData<List<Movie>> = movieRepository.getMovies()

    private fun fetchPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.fetchMovies()
        }
    }
}