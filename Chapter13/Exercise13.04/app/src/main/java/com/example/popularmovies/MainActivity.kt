package com.example.popularmovies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.popularmovies.api.MovieService
import com.example.popularmovies.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    private val movies = arrayListOf<Movie>()

    private val movieAdapter by lazy {
        MovieAdapter(movies, object : MovieAdapter.MovieClickListener {
            override fun onMovieClick(movie: Movie) {
                openMovieDetails(movie)
            }
        })
    }

    private val movieViewModel by lazy {
        ViewModelProvider(this).get(MovieViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_list.adapter = movieAdapter

        movieViewModel.getMovies().observe(this, Observer { popularMovies ->
            movies.addAll(popularMovies)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun openMovieDetails(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRA_MOVIE, movie)
        }
        startActivity(intent)
    }
}