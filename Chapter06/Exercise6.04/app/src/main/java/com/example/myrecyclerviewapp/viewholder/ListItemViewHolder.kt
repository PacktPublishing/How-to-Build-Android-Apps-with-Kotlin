package com.example.myrecyclerviewapp.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.model.ListItemUiModel

abstract class ListItemViewHolder(
    containerView: View
) : RecyclerView.ViewHolder(containerView) {
    abstract fun bindData(listItem: ListItemUiModel)
}
