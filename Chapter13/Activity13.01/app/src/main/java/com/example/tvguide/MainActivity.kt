package com.example.tvguide

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvguide.model.TVShow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tvShows = mutableListOf<TVShow>()

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

        val tvShowRepository = (application as TVApplication).tvShowRepository
        val tvShowViewModel = ViewModelProvider(this, object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TVShowViewModel(tvShowRepository) as T
            }
        }).get(TVShowViewModel::class.java)
        tvShowViewModel.getTVShows().observe(this, { shows ->
            tvShows.addAll(shows)
            tvShowAdapter.notifyDataSetChanged()
        })
        tvShowViewModel.getError().observe(this, { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        })
    }

    private fun openShowDetails(tvShow: TVShow) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRA_TV_SHOW, tvShow)
        }
        startActivity(intent)
    }
}