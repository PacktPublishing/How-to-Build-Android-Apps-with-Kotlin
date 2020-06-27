package com.example.activity03completesolution

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class Activity03 : AppCompatActivity(), MovieAdapter.MovieClickedListener {

    private lateinit var movieAdapter: MovieAdapter
    private val movies = arrayListOf<Movie>()
    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getMovies().observe(this, Observer {
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
        movieViewModel.fetchMovies()

    }

    private fun initList() {
        movieList.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(this, movies)
        movieAdapter.setClickListener(this)
        movieList.adapter = movieAdapter
    }

    override fun onMovieClick(view: View, position: Int) {
        val movieIntent = Intent(this, MovieDetailActivity::class.java)
        movieIntent.putExtra("movie_detail", movies[position])
        startActivity(movieIntent)
    }
}
