package com.example.tabnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.INDICATOR_GRAVITY_TOP
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        view_pager.adapter = MovieGenresPagerAdapter(this, supportFragmentManager)

        tabs?.tabMode = TabLayout.MODE_FIXED
        tabs?.setupWithViewPager(view_pager)
    }
}
