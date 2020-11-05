package com.android.testable.remote_media_provider

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.testable.remote_media_provider.repository.Result
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val downloadRepository = (application as RemoteProviderApplication).downloadRepository
        mainViewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(downloadRepository) as T
            }
        }).get(MainViewModel::class.java)

        mainViewModel.getDownloadLiveData()
            .observe(this, Observer { result ->
                when (result) {
                    is Result.Loading -> {
                        activity_main_progress_bar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        activity_main_progress_bar.visibility = View.GONE
                        Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG)
                            .show()
                    }
                    is Result.Error -> {
                        activity_main_progress_bar.visibility = View.GONE
                        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            })


        activity_main_recycler_view.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter(LayoutInflater.from(this)) {
            mainViewModel.downloadFile(it.url)
        }
        activity_main_recycler_view.adapter = mainAdapter
        mainViewModel.getDogsLiveData().observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    mainAdapter.updateDogs(it.data)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getDogs()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

}
