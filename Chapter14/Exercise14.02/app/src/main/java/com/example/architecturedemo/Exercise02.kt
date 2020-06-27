package com.example.architecturedemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi

class Exercise02 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise02)

        val json = "{\n" +
                "    \"fruit\": \"Apple\",\n" +
                "    \"size\": \"Large\",\n" +
                "    \"color\": \"Red\"\n" +
                "}"

        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<FruitData>(FruitData::class.java)

        val fruitData = jsonAdapter.fromJson(json)
        Log.d("Moshi logs", fruitData.toString())
    }
}
