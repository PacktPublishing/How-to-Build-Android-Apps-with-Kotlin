package com.example.myrecyclerviewapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.model.ListItemUiModel
import kotlinx.android.extensions.LayoutContainer

abstract class ListItemViewHolder(
    containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    abstract fun bindData(listItem: ListItemUiModel)
}
