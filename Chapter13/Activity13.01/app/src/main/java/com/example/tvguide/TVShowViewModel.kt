package com.example.tvguide

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tvguide.model.TVShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TVShowViewModel(private val tvShowRepository: TVShowRepository) : ViewModel() {

    init {
        fetchTVShows()
    }

    fun getTVShows(): LiveData<List<TVShow>> = tvShowRepository.tvShows.map { shows ->
        shows.sortedBy { it.name }
    }

    private fun fetchTVShows() {
        viewModelScope.launch(Dispatchers.IO) {
            tvShowRepository.fetchTVShows()
        }
    }
}