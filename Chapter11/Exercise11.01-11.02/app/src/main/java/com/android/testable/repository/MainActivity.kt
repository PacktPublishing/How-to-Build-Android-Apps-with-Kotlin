package com.android.testable.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.testable.repository.repository.Result
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postAdapter = PostAdapter(LayoutInflater.from(this))
        activity_main_recycler_view.adapter = postAdapter
        activity_main_recycler_view.layoutManager = LinearLayoutManager(this)

        val postRepository = (application as RepositoryApplication).postRepository
        val postViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PostViewModel(postRepository) as T
            }

        }).get(PostViewModel::class.java)
        postViewModel.getPosts().observe(this, Observer { result ->
            when (result) {
                is Result.Error -> {
                    Toast.makeText(applicationContext, R.string.error_message, Toast.LENGTH_LONG)
                        .show()
                    result.throwable.printStackTrace()
                }
                is Result.Loading -> {
                    // TODO show loading spinner
                }
                is Result.Success -> {
                    postAdapter.updatePosts(result.uiPosts)
                }
            }
        })
    }
}
