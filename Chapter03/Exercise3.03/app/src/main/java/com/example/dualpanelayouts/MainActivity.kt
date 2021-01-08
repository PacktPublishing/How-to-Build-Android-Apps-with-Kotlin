package com.example.dualpanelayouts

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

const val STAR_SIGN_ID = "STAR_SIGN_ID"

interface StarSignListener {
    fun onSelected(id: Int)
}

class MainActivity : AppCompatActivity(), StarSignListener {

    var isDualPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isDualPane = findViewById<View>(R.id.star_sign_detail) != null
    }

    override fun onSelected(id: Int) {

        if (isDualPane) {

            val detailFragment =
                supportFragmentManager.findFragmentById(R.id.star_sign_detail) as DetailFragment
            detailFragment.setStarSignData(id)
        } else {
            val detailIntent = Intent(this,  DetailActivity::class.java).apply {
                putExtra(STAR_SIGN_ID, id)
            }
            startActivity(detailIntent)
        }
    }
}
