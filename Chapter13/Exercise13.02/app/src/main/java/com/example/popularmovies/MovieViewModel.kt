package com.example.popularmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.popularmovies.api.MovieService
import com.example.popularmovies.model.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class MovieViewModel(private val movieService: MovieService) : ViewModel() {
    private val apiKey = "your_api_key_here"

    var popularMovies = MutableLiveData<List<Movie>>()
    private var disposable = CompositeDisposable()

    fun getPopularMovies(): LiveData<List<Movie>> = popularMovies

    fun fetchPopularMovies() {
        disposable.add(movieService.getPopularMovies(apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { Observable.fromIterable(it.results) }
            .filter {
                it.release_date.startsWith(
                    Calendar.getInstance().get(Calendar.YEAR).toString()
                )
            }
            .sorted { movie, movie2 -> movie.title.compareTo(movie2.title) }
            .map { it.copy(title = it.title.toUpperCase(Locale.getDefault())) }
            .take(4)
            .toList()
            .subscribe({
                popularMovies.postValue(it)
            }, { error ->
                Log.d("MovieViewModel", "error encountered: ${error.localizedMessage}")
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}