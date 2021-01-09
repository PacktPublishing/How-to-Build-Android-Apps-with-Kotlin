package com.example.popularmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.PopularMoviesResponse
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.assertEquals
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @InjectMocks
    lateinit var movieViewModel: MovieViewModel

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var observer: androidx.lifecycle.Observer<List<Movie>>

    @Test
    fun getPopularMovies() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val releaseDate = "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}"

        val movies = listOf(Movie(id = 3, release_date = releaseDate), Movie(id = 4, release_date = releaseDate))
        val response = PopularMoviesResponse(1, movies)

        Mockito.`when`(movieRepository.fetchMovies())
                .thenReturn(Observable.just(response))
        movieViewModel.popularMovies.observeForever(observer)
        movieViewModel.fetchPopularMovies()

        assertEquals(
                movies,
                movieViewModel.popularMovies.value
        )
    }
}