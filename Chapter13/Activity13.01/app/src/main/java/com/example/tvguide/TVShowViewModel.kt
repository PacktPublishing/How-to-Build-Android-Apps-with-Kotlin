package com.example.tvguide

import android.util.Log
import androidx.lifecycle.*
import com.example.tvguide.api.TelevisionService
import com.example.tvguide.model.TVShow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TVShowViewModel : ViewModel() {
    private val apiKey = "your_api_key_here"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val tvService by lazy { retrofit.create(TelevisionService::class.java) }

    private val _tvShows: MutableLiveData<List<TVShow>> = MutableLiveData()

    val tvShows: LiveData<List<TVShow>>
        get() = _tvShows.map { shows ->
            shows.sortedBy { it.name }
        }

    init {
        getTVShows()
    }

    private fun getTVShows() {
        viewModelScope.launch {
            try {
                val shows = tvService.getTVShows(apiKey)
                _tvShows.value = shows.results
            } catch (exception: Exception) {
                Log.d("TVShowViewModel", "Exception in getTVShows: ${exception.message}")
            }
        }
    }

}