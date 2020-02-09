package com.android.testable.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postAdapter = PostAdapter(LayoutInflater.from(this))
        activity_main_recycler_view.adapter = postAdapter
        activity_main_recycler_view.layoutManager = LinearLayoutManager(this)
        val viewModel: PostViewModel = getViewModel()
        viewModel.getPosts().observe(this, Observer {
            postAdapter.updatePosts(it)
        })
    }
}
