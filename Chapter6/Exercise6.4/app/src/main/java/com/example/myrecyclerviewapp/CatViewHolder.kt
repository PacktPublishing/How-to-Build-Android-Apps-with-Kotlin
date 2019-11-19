package com.example.myrecyclerviewapp

import android.view.View
import androidx.core.text.HtmlCompat
import com.example.myrecyclerviewapp.model.CatBreed
import com.example.myrecyclerviewapp.model.CatUiModel
import com.example.myrecyclerviewapp.model.Gender
import com.example.myrecyclerviewapp.model.ListItemUiModel
import kotlinx.android.synthetic.main.item_cat.item_cat_biography as catBioView
import kotlinx.android.synthetic.main.item_cat.item_cat_breed as catBreedView
import kotlinx.android.synthetic.main.item_cat.item_cat_gender as catGenderView
import kotlinx.android.synthetic.main.item_cat.item_cat_name as catNameView
import kotlinx.android.synthetic.main.item_cat.item_cat_photo as catPhotoView

private val FEMALE_SYMBOL by lazy {
    HtmlCompat.fromHtml("&#9793;", HtmlCompat.FROM_HTML_MODE_LEGACY)
}
private val MALE_SYMBOL by lazy {
    HtmlCompat.fromHtml("&#9794;", HtmlCompat.FROM_HTML_MODE_LEGACY)
}
private const val UNKNOWN_SYMBOL = "?"

class CatViewHolder(
    override val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : ListItemViewHolder(containerView) {
    override fun bindData(listItem: ListItemUiModel) {
        require(listItem is ListItemUiModel.Cat) { "Expected ListItemUiModel.Cat" }
        val catData = listItem.data

        containerView.setOnClickListener { onClickListener.onClick(catData) }
        imageLoader.loadImage(catData.imageUrl, catPhotoView)
        catNameView.text = catData.name
        catBreedView.text = when (catData.breed) {
            CatBreed.AmericanCurl -> "American Curl"
            CatBreed.BalineseJavanese -> "Balinese-Javanese"
            CatBreed.ExoticShorthair -> "Exotic Shorthair"
        }
        catBioView.text = catData.biography
        catGenderView.text = when (catData.gender) {
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            else -> UNKNOWN_SYMBOL
        }
    }

    interface OnClickListener {
        fun onClick(catData: CatUiModel)
    }
}
