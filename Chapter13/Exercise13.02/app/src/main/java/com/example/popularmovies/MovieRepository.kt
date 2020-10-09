package com.example.popularmovies

import com.example.popularmovies.api.MovieService

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "your_api_key_here"

    fun fetchMovies() = movieService.getPopularMovies(apiKey)
}