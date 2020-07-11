package com.example.recipebook

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recipebook.model.ListItem
import com.example.recipebook.model.RecipeUiModel
import com.example.recipebook.model.TitleUiModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_recipe.view.recipe_title
import kotlinx.android.synthetic.main.item_title.view.title_label

abstract class BaseViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    abstract fun bindData(listItem: ListItem)
}

class TitleViewHolder(override val containerView: View) : BaseViewHolder(containerView) {
    private val titleView by lazy { containerView.title_label }
    override fun bindData(listItem: ListItem) {
        titleView.text = (listItem as TitleUiModel).title
    }
}

class RecipeViewHolder(override val containerView: View) : BaseViewHolder(containerView) {
    private val titleView by lazy { containerView.recipe_title }
    override fun bindData(listItem: ListItem) {
        titleView.text = (listItem as RecipeUiModel).title
    }
}
