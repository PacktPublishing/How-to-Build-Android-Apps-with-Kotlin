package com.example.myrecyclerviewapp.viewholder

import android.view.View
import com.example.myrecyclerviewapp.model.ListItemUiModel
import kotlinx.android.synthetic.main.item_title.item_title_title as titleView

class TitleViewHolder(
    override val containerView: View
) : ListItemViewHolder(containerView) {
    override fun bindData(listItem: ListItemUiModel) {
        require(listItem is ListItemUiModel.Title) { "Expected ListItemUiModel.Title" }

        titleView.text = listItem.title
    }
}
