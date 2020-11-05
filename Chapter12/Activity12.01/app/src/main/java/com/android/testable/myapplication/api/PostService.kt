package com.android.testable.myapplication.api

import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPosts(): Call<List<Post>>
}