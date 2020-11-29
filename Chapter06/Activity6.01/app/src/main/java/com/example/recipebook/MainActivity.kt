package com.example.recipebook

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.recipebook.model.Flavor
import com.example.recipebook.model.RecipeUiModel

class MainActivity : AppCompatActivity() {
    private val recipesList: RecyclerView
            by lazy { findViewById(R.id.main_recipes_list) }
    private val addSavoryButton: View
            by lazy { findViewById(R.id.main_add_savory_button) }
    private val addSweetButton: View
            by lazy { findViewById(R.id.main_add_sweet_button) }
    private val titleView: TextView
            by lazy { findViewById(R.id.main_recipe_title) }
    private val descriptionView: TextView
            by lazy { findViewById(R.id.main_recipe_description) }

    private val recipesAdapter by lazy {
        RecipesAdapter(
            layoutInflater,
            object : RecipesAdapter.OnClickListener {
                override fun onItemClick(recipe: RecipeUiModel) {
                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setMessage(recipe.description)
                        .setPositiveButton("OK", null)
                        .create()
                        .show()
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recipesList.apply {
            adapter = recipesAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, VERTICAL, false)

            val itemTouchHelper =
                ItemTouchHelper(recipesAdapter.swipeToDeleteCallback)
            itemTouchHelper.attachToRecyclerView(this)
        }

        addSavoryButton.setOnClickListener {
            addRecipeAndClearForm(Flavor.SAVORY)
        }

        addSweetButton.setOnClickListener {
            addRecipeAndClearForm(Flavor.SWEET)
        }
    }

    private fun addRecipeAndClearForm(flavor: Flavor) {
        val title = titleView.text.toString().trim()
        val description = descriptionView.text.toString().trim()
        if (title.isEmpty() || description.isEmpty()) return

        recipesAdapter.addRecipe(
            RecipeUiModel(title, description, flavor)
        )
        titleView.text = ""
        descriptionView.text = ""
    }
}
