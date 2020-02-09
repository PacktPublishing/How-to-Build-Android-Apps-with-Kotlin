package com.android.testable.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).applicationComponent.createActivitySubcomponent()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postAdapter = PostAdapter(LayoutInflater.from(this))
        activity_main_recycler_view.adapter = postAdapter
        activity_main_recycler_view.layoutManager = LinearLayoutManager(this)
        val viewModel = ViewModelProviders.of(this, factory).get(PostViewModel::class.java)
        viewModel.getPosts().observe(this, Observer {
            postAdapter.updatePosts(it)
        })
    }
}
