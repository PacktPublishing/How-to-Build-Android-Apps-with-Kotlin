package com.example.popularmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.popularmovies.api.MovieService
import com.example.popularmovies.database.MovieDao
import com.example.popularmovies.database.MovieDatabase
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.PopularMoviesResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
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

    @Mock
    lateinit var movieDatabase: MovieDatabase

    @Test
    fun fetchCachedMovies() {
        val cachedMovies = listOf(Movie(id = 1), Movie(id = 2))
        val dao = object : MovieDao {
            override fun addMovies(movies: List<Movie>) {

            }

            override fun getMovies(): List<Movie> {
                return cachedMovies
            }

        }

        Mockito.`when`(movieDatabase.movieDao())
            .thenReturn(dao)

        runBlocking {
            movieRepository.fetchMovies()
            val movieLiveData = movieRepository.movies
            assertEquals(movieLiveData.value, cachedMovies)
        }
    }

    @Test
    fun fetchRemoteMovies() {
        val remoteMovies = listOf(Movie(id = 3), Movie(id = 4))
        val remoteResponse = PopularMoviesResponse(1, remoteMovies)

        val emptyDao = object : MovieDao {
            override fun addMovies(movies: List<Movie>) {

            }

            override fun getMovies(): List<Movie> = emptyList()

        }

        Mockito.`when`(movieDatabase.movieDao())
            .thenReturn(emptyDao)

        runBlocking {
            Mockito.`when`(movieService.getPopularMovies(anyString()))
                .thenReturn(remoteResponse)

            movieRepository.fetchMovies()
            val movieLiveData = movieRepository.movies
            assertEquals(movieLiveData.value, remoteMovies)
        }
    }
}