package com.example.tvguide

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvguide.api.TelevisionService
import com.example.tvguide.model.TVShow

class TVShowRepository(private val tvService: TelevisionService) {
    private val apiKey = "your_api_key_here"

    private val tvShowsLiveData = MutableLiveData<List<TVShow>>()
    private val errorLiveData = MutableLiveData<String>()

    val tvShows: LiveData<List<TVShow>>
        get() = tvShowsLiveData

    val error: LiveData<String>
        get() = errorLiveData

    suspend fun fetchTVShows() {
        try {
            val shows = tvService.getTVShows(apiKey)
            tvShowsLiveData.postValue(shows.results)
        } catch (exception: Exception) {
            errorLiveData.postValue("An error occurred: ${exception.message}")
        }
    }
}