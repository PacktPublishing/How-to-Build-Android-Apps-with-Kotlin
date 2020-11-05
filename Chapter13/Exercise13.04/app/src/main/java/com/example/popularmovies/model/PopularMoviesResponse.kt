package com.example.popularmovies.model

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movie>
)