package com.example.popularmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.model.Movie
import org.junit.Assert.assertEquals
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

    @InjectMocks
    lateinit var movieViewModel: MovieViewModel

    @Mock
    lateinit var movieRepository: MovieRepository

    @Test
    fun getPopularMovies() {
        val movieLiveData = MutableLiveData<List<Movie>>()
        val popularMovies = listOf(
            Movie(
                title = "Title",
                release_date = Calendar.getInstance().get(Calendar.YEAR).toString()
            )
        )
        movieLiveData.postValue(popularMovies)

        Mockito.`when`(movieRepository.movies)
            .thenReturn(movieLiveData)

        assertEquals(
            movieLiveData.value,
            movieViewModel.getPopularMovies().value
        )
    }

}