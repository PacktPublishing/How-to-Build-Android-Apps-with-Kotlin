package com.example.popularmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.popularmovies.api.MovieService
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.PopularMoviesResponse
import io.reactivex.rxjava3.core.Observable
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @InjectMocks
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var movieService: MovieService

    @Test
    fun getPopularMovies() {
        val year = Calendar.getInstance().get(Calendar.YEAR).toString()
        val movies = listOf(Movie(id = 3, release_date = year), Movie(id = 4, release_date = year))
        val response = PopularMoviesResponse(1, movies)

        Mockito.`when`(movieService.getPopularMovies(anyString()))
            .thenReturn(Observable.just(response))

        movieRepository.fetchMovies()
            .test()
            .await()
            .assertNoErrors()
            .assertValue(response)
    }


}