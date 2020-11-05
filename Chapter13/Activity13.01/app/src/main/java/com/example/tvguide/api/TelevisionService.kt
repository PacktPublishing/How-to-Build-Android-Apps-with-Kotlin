package com.example.tvguide.api

import com.example.tvguide.model.TVResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TelevisionService {

    @GET("tv/on_the_air")
    suspend fun getTVShows(@Query("api_key") apiKey: String): TVResponse
}