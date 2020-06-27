package com.example.activity03completesolution

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.androidworkshop.network.MovieApi
import com.example.androidworkshop.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val movieResponseLiveData = MutableLiveData<MovieListResponse>()
    private val movieListLiveData = Transformations.map(movieResponseLiveData) {
        it.results.map { movie ->
            movie.copy(title = movie.title.toUpperCase())
        }
    }
    private val retrofit = RetrofitClient.getRetrofitClient()

    fun getMovies(): LiveData<List<Movie>> {
        return movieListLiveData
    }

    fun fetchMovies() {
        CoroutineScope(IO).launch {
            val movieApi = retrofit.create(MovieApi::class.java)
            val movies = movieApi.fetchPopularMovies("1d9a4704d81b27085f142914119d38fe")
            movieResponseLiveData.postValue(movies)
        }
    }
}