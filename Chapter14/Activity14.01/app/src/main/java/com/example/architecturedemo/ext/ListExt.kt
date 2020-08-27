package com.example.architecturedemo.ext

import com.example.architecturedemo.database.DBMovie
import com.example.architecturedemo.model.Movie

fun List<DBMovie>.mapToMovie(): List<Movie> {
    return this.map {
        Movie(
            it.uid,
            it.movieTitle,
            it.vote
        )
    }
}

fun List<Movie>.mapToDbMovie(): List<DBMovie> {
    return this.map {
        DBMovie(
            it.id,
            it.title,
            it.vote_average
        )
    }
}