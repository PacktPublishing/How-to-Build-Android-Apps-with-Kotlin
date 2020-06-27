package com.example.activity02completesolution.network

import com.example.activity02completesolution.model.MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Observable<MovieListResponse>
}