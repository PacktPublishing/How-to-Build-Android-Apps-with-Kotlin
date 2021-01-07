package com.android.testable.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.testable.repository.repository.UiPost

class PostAdapter(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val posts = mutableListOf<UiPost>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(layoutInflater.inflate(R.layout.view_post_row, parent, false))

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    fun updatePosts(posts: List<UiPost>) {
        this.posts.clear()
        this.posts.addAll(posts)
        this.notifyDataSetChanged()
    }

    inner class PostViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

        private val titleTextView: TextView =
            containerView.findViewById<TextView>(R.id.view_post_row_title)
        private val bodyTextView: TextView =
            containerView.findViewById<TextView>(R.id.view_post_row_body)

        fun bind(post: UiPost) {
            bodyTextView.text = post.body
            titleTextView.text = post.title
        }
    }
}