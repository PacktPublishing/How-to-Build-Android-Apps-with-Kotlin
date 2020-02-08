package com.example.newyorkweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newyorkweather.api.OpenWeatherMapService
import com.example.newyorkweather.model.OpenWeatherMapResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        weatherApiService
            .getWeather("New York", "[YOUR TOKEN]")
            .enqueue(object : Callback<OpenWeatherMapResponseData> {
                override fun onFailure(call: Call<OpenWeatherMapResponseData>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<OpenWeatherMapResponseData>,
                    response: Response<OpenWeatherMapResponseData>
                ) {
                }
            })
    }
}
