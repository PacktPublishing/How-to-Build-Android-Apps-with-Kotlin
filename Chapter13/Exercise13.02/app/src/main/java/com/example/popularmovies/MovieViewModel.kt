package com.example.popularmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.popularmovies.model.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val popularMoviesLiveData = MutableLiveData<List<Movie>>()

    val popularMovies: LiveData<List<Movie>>
        get() = popularMoviesLiveData

    private var disposable = CompositeDisposable()

    fun fetchPopularMovies() {
        disposable.add(movieRepository.fetchMovies()
                .subscribeOn(Schedulers.io())
                .flatMap { Observable.fromIterable(it.results) }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    popularMoviesLiveData.postValue(it)
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