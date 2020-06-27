package com.example.architecturedemo.top_rated

import android.app.Application
import com.example.architecturedemo.CoroutineTestRule
import com.example.architecturedemo.top_rated.MovieRepository
import com.example.architecturedemo.top_rated.MovieViewModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var sut: MovieViewModel
    @Mock
    lateinit var application: Application
    @Mock
    lateinit var movieRepository: MovieRepository

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
    }

    @Test
    fun `should invoke fetchMovies of MovieRepository when fetchMovies is called`() =
        runBlocking {
            sut = MovieViewModel(
                application,
                movieRepository,
                coroutinesTestRule.testDispatcherProvider
            )
            sut.fetchMovies()
            verify(movieRepository, times(1)).fetchMovies()
        }
}