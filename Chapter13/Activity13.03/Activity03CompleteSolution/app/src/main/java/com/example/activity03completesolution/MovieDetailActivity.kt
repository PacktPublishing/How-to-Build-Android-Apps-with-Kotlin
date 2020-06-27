package com.example.activity03completesolution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movie = intent.getParcelableExtra<Movie>("movie_detail")
        movie?.run {
            tvTitle.text = String.format(getString(R.string.title, title))
            tvOverview.text = String.format(getString(R.string.overview, overview))
            tvReleaseData.text = String.format(getString(R.string.release_date, release_date))
            tvPopularity.text = String.format(getString(R.string.popularity, popularity.toString()))
            tvLanguage.text = String.format(getString(R.string.language, original_language))
        }
    }
}
