package com.example.tvguide

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tvguide.database.TVDatabase
import com.example.tvguide.model.TVShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TVShowViewModel(application: Application) : AndroidViewModel(application) {
    private val tvShowRepository: TVShowRepository by lazy {
        TVShowRepository(TVDatabase.getInstance(application.applicationContext))
    }

    private var _tvShows: LiveData<List<TVShow>> = tvShowRepository.getTVShows()

    val tvShows: LiveData<List<TVShow>>
        get() = _tvShows.map { shows ->
            shows.sortedBy { it.name }
        }

    init {
        getTVShows()
    }

    private fun getTVShows() {
        viewModelScope.launch(Dispatchers.IO) {
            tvShowRepository.fetchTVShows()
        }
    }
}