package com.example.exercisedemo.top_rated

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercisedemo.R
import com.example.exercisedemo.database.MovieDatabase
import com.example.exercisedemo.model.Movie
import com.example.exercisedemo.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*

class Exercise03 : AppCompatActivity() {

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
                    RetrofitClient.getRetrofitClient(),
                    MovieDatabase.getInstance(application)
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
        movieAdapter =
            MovieAdapter(this, movies)
        movieList.adapter = movieAdapter
    }
}
