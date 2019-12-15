package com.android.testable.repository.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.testable.repository.api.Post
import com.android.testable.repository.api.PostService
import com.android.testable.repository.db.PostDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class PostRepositoryImpl(
    private val postService: PostService,
    private val postDao: PostDao,
    private val postMapper: PostMapper,
    private val executor: Executor
) : PostRepository {

    override fun getPosts(): LiveData<Result> {
        val result = MutableLiveData<Result>()
        result.postValue(Result.Loading)
        postService.getPosts().enqueue(object : Callback<List<Post>> {

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                result.postValue(Result.Error(t))
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        executor.execute {
                            postDao.insertPosts(posts.map { post ->
                                postMapper.serviceToEntity(post)
                            })
                            result.postValue(Result.Success(posts.map { post ->
                                postMapper.serviceToUi(post)
                            }))
                        }
                    }
                } else {
                    result.postValue(Result.Error(RuntimeException("Unexpected error")))
                }
            }
        })
        return result
    }
}