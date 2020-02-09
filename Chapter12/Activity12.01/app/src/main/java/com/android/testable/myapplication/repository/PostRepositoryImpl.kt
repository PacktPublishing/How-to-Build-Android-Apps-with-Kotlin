package com.android.testable.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.testable.myapplication.api.Post
import com.android.testable.myapplication.api.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepositoryImpl(private val postService: PostService) : PostRepository {

    override fun getPosts(): LiveData<List<Post>> {
        val result = MutableLiveData<List<Post>>()
        postService.getPosts().enqueue(object : Callback<List<Post>> {

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    result.postValue(response.body())
                }
            }
        })
        return result
    }
}