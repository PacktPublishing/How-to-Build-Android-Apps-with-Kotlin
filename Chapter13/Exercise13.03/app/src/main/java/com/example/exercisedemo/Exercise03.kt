package com.example.exercisedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class Exercise03 : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProvider(
            this,
            UserViewModel.Factory(
                application
            )
        ).get(
            UserViewModel::class.java
        )
        userViewModel.userNames.observe(this, Observer { usenames ->
            Log.d("Coroutine logs", usenames.toString())
        })

        userViewModel.addUser(User(Random.nextInt(), "don", "Morris"))
        userViewModel.getUsers()
    }
}