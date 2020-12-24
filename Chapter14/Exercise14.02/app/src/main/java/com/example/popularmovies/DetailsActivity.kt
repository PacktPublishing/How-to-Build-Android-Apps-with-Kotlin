package com.example.popularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.popularmovies.model.Movie

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "movie"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w185/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val titleText: TextView = findViewById(R.id.title_text)
        val releaseText: TextView = findViewById(R.id.release_text)
        val overviewText: TextView = findViewById(R.id.overview_text)
        val poster: ImageView = findViewById(R.id.movie_poster)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        movie?.run {
            titleText.text = title
            releaseText.text = release_date.take(4)

            overviewText.text = "Overview: $overview"

            Glide.with(this@DetailsActivity)
                    .load("$IMAGE_URL$poster_path")
                    .placeholder(R.mipmap.ic_launcher)
                    .fitCenter()
                    .into(poster)
        }
    }
}