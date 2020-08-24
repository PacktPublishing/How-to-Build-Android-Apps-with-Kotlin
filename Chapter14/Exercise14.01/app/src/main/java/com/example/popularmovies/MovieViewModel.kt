package com.example.popularmovies

import androidx.lifecycle.*
import com.example.popularmovies.api.MovieService
import com.example.popularmovies.model.Movie
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MovieViewModel : ViewModel() {
    private val apiKey = "your_api_key_here"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val movieService by lazy { retrofit.create(MovieService::class.java) }

    private val movies: MutableLiveData<List<Movie>> = MutableLiveData()

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
        viewModelScope.launch {
            val popularMovies = movieService.getPopularMovies(apiKey)
            movies.value = popularMovies.results
        }
    }
}