package com.example.popularmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularmovies.api.MovieService
import com.example.popularmovies.model.Movie
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel : ViewModel() {
    private val apiKey = "your_api_key_here"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val movieService by lazy { retrofit.create(MovieService::class.java) }

    private val movies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>().also {
            fetchPopularMovies()
        }
    }

    private val idLiveData: LiveData<Int> = MutableLiveData()
    fun getMovies(): LiveData<List<Movie>> {
        return movies
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                val popularMovies = movieService.getPopularMovies(apiKey)
                movies.value = popularMovies.results
            } catch (exception: Exception) {
                Log.d("MovieViewModel", "Exception in fetchPopularMovies: ${exception.message}")
            }
        }
    }
}