package com.example.architecturedemo.top_rated

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturedemo.R
import com.example.architecturedemo.database.MovieDatabase
import com.example.architecturedemo.model.Movie
import com.example.architecturedemo.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*

class Activity01 : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private val movies = arrayListOf<Movie>()
    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
        movieViewModel =
            ViewModelProvider(
                this,
                MovieViewModel.Factory(
                    application,
                    MovieRepository(
                        RetrofitClient.getRetrofitClient(),
                        MovieDatabase.getInstance(application)
                    )
                )
            ).get(
                MovieViewModel::class.java
            )
        movieViewModel.getMovies().observe(this, Observer {
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
        movieViewModel.fetchMovies()
    }

    private fun initList() {
        movieList.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(this, movies)
        movieList.adapter = movieAdapter
    }
}
