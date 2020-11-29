package com.example.myrecyclerviewapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.model.CatBreed
import com.example.myrecyclerviewapp.model.CatUiModel
import com.example.myrecyclerviewapp.model.Gender
import com.example.myrecyclerviewapp.model.ListItemUiModel

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView
            by lazy { findViewById(R.id.recycler_view) }
    private val addItemButton: View
            by lazy { findViewById(R.id.main_add_item_button) }

    private val listItemsAdapter by lazy {
        ListItemsAdapter(
            layoutInflater,
            GlideImageLoader(this),
            object : ListItemsAdapter.OnClickListener {
                override fun onItemClick(catData: CatUiModel) = showSelectionDialog(catData)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = listItemsAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemTouchHelper = ItemTouchHelper(listItemsAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        addItemButton.setOnClickListener {
            listItemsAdapter.addItem(
                1,
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Female,
                        CatBreed.BalineseJavanese,
                        "Anonymous",
                        "Unknown",
                        "https://cdn2.thecatapi.com/images/zJkeHza2K.jpg"
                    )
                )
            )
        }
        listItemsAdapter.setData(
            listOf(
                ListItemUiModel.Title("Sleeper Agents"),
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Male,
                        CatBreed.ExoticShorthair,
                        "Garfield",
                        "Garfield is as a lazy, fat, and cynical orange cat.",
                        "https://cdn2.thecatapi.com/images/FZpeiLi4n.jpg"
                    )
                ),
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Unknown,
                        CatBreed.AmericanCurl,
                        "Curious George",
                        "Award winning investigator",
                        "https://cdn2.thecatapi.com/images/vJB8rwfdX.jpg"
                    )
                ),
                ListItemUiModel.Title("Active Agents"),
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Male,
                        CatBreed.BalineseJavanese,
                        "Fred",
                        "Silent and deadly",
                        "https://cdn2.thecatapi.com/images/DBmIBhhyv.jpg"
                    )
                ),
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Female,
                        CatBreed.ExoticShorthair,
                        "Wilma",
                        "Cuddly assassin",
                        "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg"
                    )
                ),
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Male,
                        CatBreed.ExoticShorthair,
                        "Tom",
                        "Tom, AKA Jasper, is very energetic, determined yet somewhat... Slow.",
                        "https://cdn2.thecatapi.com/images/y61B6bFCh.jpg"
                    )
                )
            )
        )
    }

    private fun showSelectionDialog(catData: CatUiModel) {
        AlertDialog.Builder(this)
            .setTitle("Agent Selected")
            .setMessage("You have selected agent ${catData.name}")
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}
