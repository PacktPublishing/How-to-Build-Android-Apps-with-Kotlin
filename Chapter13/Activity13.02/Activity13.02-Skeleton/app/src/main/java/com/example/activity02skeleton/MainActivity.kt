package com.example.activity02skeleton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , MovieAdapter.MovieClickedListener {

    lateinit var movieAdapter: MovieAdapter
    val movies = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movies.add(
            Movie(
                "Star Wars", "1977-05-25", "en",
                "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire.", 96.901))
        movies.add(
            Movie(
                "Big Hero 6", "2014-10-24", "en",
                "The special bond that develops between plus-sized inflatable robot Baymax, and prodigy Hiro Hamada.", 172.074))
        initList()
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
