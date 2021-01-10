package com.example.tvguide.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tvguide.TVShowAdapter
import com.example.tvguide.model.TVShow

@BindingAdapter("list")
fun bindTVShows(view: RecyclerView, tvShows: List<TVShow>?) {
    val adapter = view.adapter as TVShowAdapter
    adapter.addTVShows(tvShows ?: emptyList())
}