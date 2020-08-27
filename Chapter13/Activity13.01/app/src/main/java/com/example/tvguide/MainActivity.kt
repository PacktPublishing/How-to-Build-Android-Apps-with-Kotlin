package com.example.tvguide

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tvguide.api.TelevisionService
import com.example.tvguide.model.TVShow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val apiKey = "your_api_key_here"

    private val tvShows = arrayListOf<TVShow>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val televisionService by lazy { retrofit.create(TelevisionService::class.java) }

    private val tvShowAdapter by lazy {
        TVShowAdapter(tvShows, object : TVShowAdapter.TVClickListener {
            override fun onShowClick(show: TVShow) {
                openShowDetails(show)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_show_list.adapter = tvShowAdapter

        getTVShows()
    }

    private fun getTVShows() {
        CoroutineScope(Dispatchers.IO).launch {
            val shows = televisionService.getTVShows(apiKey)
            withContext(Dispatchers.Main) {
                tvShows.addAll(shows.results.sortedBy { it.name })
                tvShowAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun openShowDetails(tvShow: TVShow) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRA_TV_SHOW, tvShow)
        }
        startActivity(intent)
    }
}