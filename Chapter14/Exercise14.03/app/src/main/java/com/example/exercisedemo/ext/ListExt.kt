package com.example.exercisedemo.ext

import com.example.exercisedemo.database.DBMovie
import com.example.exercisedemo.model.Movie

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