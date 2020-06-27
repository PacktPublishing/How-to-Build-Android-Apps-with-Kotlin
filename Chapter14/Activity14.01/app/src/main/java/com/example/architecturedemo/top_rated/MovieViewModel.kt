package com.example.architecturedemo.top_rated

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.topratedmoviewitharchitecturepattern.DefaultDispatcherProvider
import com.example.topratedmoviewitharchitecturepattern.DispatcherProvider
import com.example.architecturedemo.model.Movie
import kotlinx.coroutines.launch

class MovieViewModel(
    private val application: Application,
    private val movieRepository: MovieRepository,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : ViewModel() {
    private var movieResponseLiveData: MutableLiveData<List<Movie>> = movieRepository.getMovies()

    fun getMovies(): MutableLiveData<List<Movie>> {
        return movieResponseLiveData
    }

    fun fetchMovies() {
        viewModelScope.launch(dispatchers.io()){
            movieRepository.fetchMovies()
        }

    }

    class Factory(
        private val application: Application,
        private val movieRepository: MovieRepository
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(application, movieRepository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}