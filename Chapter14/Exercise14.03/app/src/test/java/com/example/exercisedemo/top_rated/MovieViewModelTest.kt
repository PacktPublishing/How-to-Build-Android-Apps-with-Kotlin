package com.example.exercisedemo.top_rated

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercisedemo.database.DBMovie
import com.example.exercisedemo.database.MovieDao
import com.example.exercisedemo.database.MovieDatabase
import com.example.exercisedemo.ext.mapToMovie
import com.example.exercisedemo.model.Movie
import com.example.exercisedemo.model.MovieListResponse
import com.example.exercisedemo.network.MovieApi
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var sut: MovieViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var db: MovieDatabase

    @Mock
    lateinit var application: Application

    lateinit var dbDao: MovieDao
    lateinit var emptyDbDao: MovieDao
    lateinit var movieApi: MovieApi
    val cachedMovies = listOf(DBMovie(1, "22", 2.2))
    val movieResponse = MovieListResponse("1", arrayListOf(Movie(1, "22", 2.2)))
    private val mainTestThread = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        dbDao = object : MovieDao {
            override suspend fun getAll(): List<DBMovie> {
                return listOf(DBMovie(1, "22", 2.2))
            }

            override suspend fun addMovies(movies: List<DBMovie>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        emptyDbDao = object : MovieDao {
            override suspend fun getAll(): List<DBMovie> {
                return listOf()
            }

            override suspend fun addMovies(movies: List<DBMovie>) {
                movies
            }
        }
        movieApi = object : MovieApi {
            override suspend fun fetchTopRatedMovies(apiKey: String): MovieListResponse {
                return movieResponse
            }
        }
    }

    @Test
    fun `when fetchMovies is called then return movies from cache if cache is not empty`() = runBlocking{
        sut = MovieViewModel(application, retrofit, db)
        whenever(db.movieDao()).thenReturn(dbDao)
        val movieLiveData = sut.getMovies()

        sut.fetchMovies().await()

        assertEquals(movieLiveData.value, cachedMovies.mapToMovie())
    }

    @Test
    fun `when fetchMovies is called then return movies from API call response if cache is empty`() = runBlocking {
        sut = MovieViewModel(application, retrofit, db)
        whenever(db.movieDao()).thenReturn(emptyDbDao)
        whenever(retrofit.create(MovieApi::class.java)).thenReturn(movieApi)
        val movieLiveData = sut.getMovies()

        sut.fetchMovies().await()

        assertEquals(movieLiveData.value, movieResponse.results)

    }
}