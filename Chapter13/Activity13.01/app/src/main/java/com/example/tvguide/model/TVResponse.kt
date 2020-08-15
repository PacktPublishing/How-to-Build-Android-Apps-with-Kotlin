package com.example.tvguide.model

data class TVResponse(
    val page: Int,
    val results: List<TVShow>
)