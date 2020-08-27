package com.example.architecturedemo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FruitData(
    val fruit: String,
    val size: String,
    val color: String
)