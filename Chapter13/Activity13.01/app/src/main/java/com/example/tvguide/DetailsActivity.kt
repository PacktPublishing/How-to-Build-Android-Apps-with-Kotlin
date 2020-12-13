package com.example.tvguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tvguide.model.TVShow
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW = "tvshow"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w185/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val tvShow = intent.getParcelableExtra<TVShow>(EXTRA_TV_SHOW)
        tvShow?.run {
            title_text.text = name
            release_text.text = "First Air Date: ${first_air_date.take(4)}"
            overview_text.text = "Overview: $overview"

            Glide.with(this@DetailsActivity)
                .load("$IMAGE_URL$poster_path")
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(tv_poster)
        }
    }
}