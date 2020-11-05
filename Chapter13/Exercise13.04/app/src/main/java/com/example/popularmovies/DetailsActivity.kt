package com.example.popularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.popularmovies.model.Movie
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private val imageUrl = "https://image.tmdb.org/t/p/w185/"

    companion object {
        const val EXTRA_MOVIE = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        movie?.run {
            title_text.text = title
            release_text.text = release_date.take(4)

            overview_text.text = "Overview: $overview"
            val imagePath = imageUrl + poster_path
            Glide.with(this@DetailsActivity)
                .load(imagePath)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(movie_poster)
        }
    }
}