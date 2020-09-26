package com.example.tvguide

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.example.tvguide.api.TelevisionService
import com.example.tvguide.database.TVDao
import com.example.tvguide.database.TVDatabase
import com.example.tvguide.model.TVResponse
import com.example.tvguide.model.TVShow
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
class TVShowRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var tvShowRepository: TVShowRepository

    @Mock
    lateinit var tvService: TelevisionService

    @Mock
    lateinit var tvDatabase: TVDatabase

    @Test
    fun fetchCachedTVShows() {
        val cachedTVShows = listOf(TVShow(id = 1), TVShow(id = 2))
        val dao = object : TVDao {
            override fun addTVShows(tvShows: List<TVShow>) {

            }

            override fun getTVShows(): List<TVShow> {
                return cachedTVShows
            }

        }

        Mockito.`when`(tvDatabase.tvDao())
            .thenReturn(dao)

        runBlocking {
            tvShowRepository.fetchTVShows()
            val tvShowLiveData = tvShowRepository.getTVShows()
            assertEquals(tvShowLiveData.value, cachedTVShows)
        }
    }

    @Test
    fun fetchRemoteTVShows() {
        val remoteTVShows = listOf(TVShow(id = 3), TVShow(id = 4))
        val remoteResponse = TVResponse(1, remoteTVShows)

        val emptyDao = object : TVDao {
            override fun addTVShows(TVShows: List<TVShow>) {

            }

            override fun getTVShows(): List<TVShow> = emptyList()

        }

        Mockito.`when`(tvDatabase.tvDao())
            .thenReturn(emptyDao)

        runBlocking {
            Mockito.`when`(tvService.getTVShows(anyString()))
                .thenReturn(remoteResponse)

            tvShowRepository.fetchTVShows()
            val liveData = tvShowRepository.getTVShows()
            assertEquals(liveData.value, remoteTVShows)
        }
    }
}