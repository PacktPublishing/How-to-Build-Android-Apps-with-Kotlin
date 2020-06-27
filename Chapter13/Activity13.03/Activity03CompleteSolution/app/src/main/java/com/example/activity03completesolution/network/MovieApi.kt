package com.example.androidworkshop.network

import com.example.activity03completesolution.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey: String
    ): MovieListResponse
}