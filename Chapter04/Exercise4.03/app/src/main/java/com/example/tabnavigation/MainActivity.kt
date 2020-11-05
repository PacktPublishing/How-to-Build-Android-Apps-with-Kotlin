package com.example.tabnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = MovieGenresPagerAdapter(this, supportFragmentManager)

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.tabMode = TabLayout.MODE_FIXED
        tabs.setupWithViewPager(viewPager)
    }
}
