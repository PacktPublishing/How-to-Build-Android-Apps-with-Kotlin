package com.example.tvguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tvguide.model.TVShow

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW = "tvshow"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w185/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val titleText: TextView = findViewById(R.id.title_text)
        val releaseText: TextView = findViewById(R.id.release_text)
        val overviewText: TextView = findViewById(R.id.overview_text)
        val poster: ImageView = findViewById(R.id.tv_poster)

        val tvShow = intent.getParcelableExtra<TVShow>(EXTRA_TV_SHOW)
        tvShow?.run {
            titleText.text = name
            releaseText.text = "First Air Date: ${first_air_date.take(4)}"
            overviewText.text = "Overview: $overview"

            Glide.with(this@DetailsActivity)
                .load("$IMAGE_URL$poster_path")
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(poster)
        }
    }
}