package com.example.tvguide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvguide.model.TVShow
import kotlinx.android.synthetic.main.view_tv_show_item.view.*

class TVShowAdapter(private val tvShows: List<TVShow>, private val clickListener: TVClickListener) : RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {

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

    class TVShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bind(show: TVShow) {
            itemView.tv_show_title.text = show.name

            val imagePath = imageUrl + show.poster_path

            Glide.with(itemView.context)
                .load(imagePath)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(itemView.tv_poster)
        }
    }

    interface TVClickListener {
        fun onShowClick(show: TVShow)
    }
}