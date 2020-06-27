package com.example.architecturedemo.top_rated

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.architecturedemo.database.DBMovie
import com.example.architecturedemo.database.MovieDao
import com.example.architecturedemo.database.MovieDatabase
import com.example.architecturedemo.ext.mapToMovie
import com.example.architecturedemo.model.Movie
import com.example.architecturedemo.model.MovieListResponse
import com.example.architecturedemo.network.MovieApi
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {
    private lateinit var sut: MovieRepository
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var retrofit: Retrofit
    @Mock
    lateinit var db: MovieDatabase

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
    fun `when fetchMovies is called then return movies from cache if cache is not empty`() =
        runBlockingTest {
            sut = MovieRepository(retrofit, db)
            whenever(db.movieDao()).thenReturn(dbDao)
            //whenever(dbDao.getAll()).thenReturn(cachedMovies)
            val movieLiveData = sut.getMovies()


            sut.fetchMovies()

            assertEquals(movieLiveData.value, cachedMovies.mapToMovie())

        }

    @Test
    fun `when fetchMovies is called then return movies from API call response if cache is empty`() =
        runBlockingTest {
            sut = MovieRepository(retrofit, db)
            whenever(db.movieDao()).thenReturn(emptyDbDao)
            whenever(retrofit.create(MovieApi::class.java)).thenReturn(movieApi)
            val movieLiveData = sut.getMovies()


            sut.fetchMovies()

            assertEquals(movieLiveData.value, movieResponse.results)

        }

}