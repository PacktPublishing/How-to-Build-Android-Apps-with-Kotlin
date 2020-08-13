package com.example.popularmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovies.model.Movie
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.view_movie_item.view.*

class MovieAdapter(private val movies: List<Movie>, private val clickListener: MovieClickListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener.onMovieClick(movie) }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(movie: Movie) {
            itemView.movie_title.text = movie.title

            val imagePath = imageUrl + movie.poster_path

            Glide.with(itemView.context)
                .load(imagePath)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(itemView.movie_poster)
        }
    }

    interface MovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}