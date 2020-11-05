package com.example.popularmovies.api

import com.example.popularmovies.model.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): PopularMoviesResponse
}