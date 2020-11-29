package com.example.myrecyclerviewapp.viewholder

import android.view.View
import android.widget.TextView
import com.example.myrecyclerviewapp.R
import com.example.myrecyclerviewapp.model.ListItemUiModel

class TitleViewHolder(
    containerView: View
) : ListItemViewHolder(containerView) {
    private val titleView: TextView
            by lazy { containerView.findViewById(R.id.item_title_title) }

    override fun bindData(listItem: ListItemUiModel) {
        require(listItem is ListItemUiModel.Title) {
            "Expected ListItemUiModel.Title"
        }

        titleView.text = listItem.title
    }
}
