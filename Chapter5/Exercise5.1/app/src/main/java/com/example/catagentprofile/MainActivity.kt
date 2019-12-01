package com.example.catagentprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catagentprofile.api.TheCatApiService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    private val theCatApiService by lazy { retrofit.create(TheCatApiService::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
