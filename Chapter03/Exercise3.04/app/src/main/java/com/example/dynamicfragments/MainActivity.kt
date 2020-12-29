package com.example.dynamicfragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), StarSignListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment_container?.let { frameLayout ->

            val listFragment = ListFragment()

            supportFragmentManager.beginTransaction()
                .add(frameLayout.id, listFragment).commit()
        }
    }

    override fun onSelected(starSignId: Int) {

        fragment_container?.let {frameLayout ->

            val detailFragment = DetailFragment.newInstance(starSignId)

            supportFragmentManager.beginTransaction()
                .replace(frameLayout.id, detailFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
