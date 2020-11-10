package com.android.testable.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.testable.repository.repository.UiPost
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_post_row.view.*

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

    inner class PostViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(post: UiPost) {
            containerView.view_post_row_body.text = post.body
            containerView.view_post_row_title.text = post.title
        }
    }
}