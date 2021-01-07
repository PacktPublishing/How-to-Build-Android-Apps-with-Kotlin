package com.android.testable.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Activity2 : AppCompatActivity() {

    companion object {
        const val EXTRA_ITEM_COUNT = "EXTRA_ITEM_COUNT"

        fun newIntent(context: Context, itemCount: Int) = Intent(context, Activity2::class.java).putExtra(EXTRA_ITEM_COUNT, itemCount)
    }

    private lateinit var adapter : ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val recyclerView = findViewById<RecyclerView>(R.id.activity_2_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter= ItemAdapter(LayoutInflater.from(this)) {
            startActivity(Activity3.newIntent(this, it))
        }
        recyclerView.adapter = adapter
        (application as MyApplication).itemGenerator.generateItemsAsync(intent.getIntExtra(EXTRA_ITEM_COUNT, 0)) {
            runOnUiThread {
                adapter.addItems(it)
            }
        }
    }
}