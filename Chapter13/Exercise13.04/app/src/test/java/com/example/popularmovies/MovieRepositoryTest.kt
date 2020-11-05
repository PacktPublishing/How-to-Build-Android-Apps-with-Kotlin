package com.example.popularmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.popularmovies.api.MovieService
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.PopularMoviesResponse

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var movieService: MovieService

    @Test
    fun fetchMovies() {
        val movies = listOf(Movie(id = 3), Movie(id = 4))
        val response = PopularMoviesResponse(1, movies)

        runBlocking {
            Mockito.`when`(movieService.getPopularMovies(anyString()))
                .thenReturn(response)

            movieRepository.fetchMovies()
            val movieLiveData = movieRepository.movies
            assertEquals(movieLiveData.value, movies)
        }
    }
}