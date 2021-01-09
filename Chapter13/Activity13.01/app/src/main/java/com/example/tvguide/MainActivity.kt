package com.example.tvguide

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tvguide.model.TVShow

class MainActivity : AppCompatActivity() {

    private val tvShowAdapter by lazy {
        TVShowAdapter(object : TVShowAdapter.TVClickListener {
            override fun onShowClick(show: TVShow) {
                openShowDetails(show)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvShowRecyclerView: RecyclerView = findViewById(R.id.tv_show_list)
        tvShowRecyclerView.adapter = tvShowAdapter

        val tvShowRepository = (application as TVApplication).tvShowRepository
        val tvShowViewModel = ViewModelProvider(this, object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TVShowViewModel(tvShowRepository) as T
            }
        }).get(TVShowViewModel::class.java)
        tvShowViewModel.getTVShows().observe(this, { shows ->
            tvShowAdapter.addTVShows(shows)
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