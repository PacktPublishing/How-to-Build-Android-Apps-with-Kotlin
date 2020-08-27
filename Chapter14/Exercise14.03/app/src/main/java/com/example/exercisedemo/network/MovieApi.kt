package com.example.exercisedemo.network

import com.example.exercisedemo.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("api_key") apiKey: String
    ): MovieListResponse
}