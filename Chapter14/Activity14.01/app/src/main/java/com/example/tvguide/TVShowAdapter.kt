package com.example.tvguide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvguide.model.TVShow

class TVShowAdapter(private val clickListener: TVClickListener) : RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {

    private val tvShows = mutableListOf<TVShow>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_tv_show_item, parent, false)
        return TVShowViewHolder(view)
    }

    override fun getItemCount() = tvShows.size

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.bind(tvShow)
        holder.itemView.setOnClickListener { clickListener.onShowClick(tvShow) }
    }

    fun addTVShows(shows: List<TVShow>) {
        tvShows.addAll(shows)
        notifyItemRangeInserted(0, shows.size)
    }

    class TVShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"
        private val titleText: TextView by lazy {
            itemView.findViewById(R.id.tv_show_title)
        }
        private val poster: ImageView by lazy {
            itemView.findViewById(R.id.tv_poster)
        }

        fun bind(show: TVShow) {
            titleText.text = show.name

            Glide.with(itemView.context)
                .load("$imageUrl${show.poster_path}")
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(poster)
        }
    }

    interface TVClickListener {
        fun onShowClick(show: TVShow)
    }
}