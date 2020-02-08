package com.example.newyorkweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newyorkweather.api.OpenWeatherMapService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    private val weatherApiService by lazy {
        retrofit.create(OpenWeatherMapService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
