package com.example.tvguide

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.tvguide.databinding.ActivityMainBinding
import com.example.tvguide.model.TVShow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tvShows = arrayListOf<TVShow>()

    private val tvShowAdapter by lazy {
        TVShowAdapter(tvShows, object : TVShowAdapter.TVClickListener {
            override fun onShowClick(show: TVShow) {
                openShowDetails(show)
            }
        })
    }

    private val tvShowViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TVShowViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        tv_show_list.adapter = tvShowAdapter

        binding.viewModel = tvShowViewModel
        binding.lifecycleOwner = this
    }

    private fun openShowDetails(tvShow: TVShow) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRA_TV_SHOW, tvShow)
        }
        startActivity(intent)
    }
}