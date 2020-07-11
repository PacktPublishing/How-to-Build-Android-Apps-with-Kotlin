package com.example.recipebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.recipebook.model.Flavor
import com.example.recipebook.model.RecipeUiModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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

        main_recipes_list.adapter = recipesAdapter
        main_recipes_list.layoutManager =
            LinearLayoutManager(this, VERTICAL, false)

        val itemTouchHelper =
            ItemTouchHelper(recipesAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(main_recipes_list)

        main_add_savory_button.setOnClickListener {
            addRecipeAndClearForm(Flavor.SAVORY)
        }

        main_add_sweet_button.setOnClickListener {
            addRecipeAndClearForm(Flavor.SWEET)
        }
    }

    private fun addRecipeAndClearForm(flavor: Flavor) {
        val title = main_recipe_title.text.toString().trim()
        val description = main_recipe_description.text.toString().trim()
        if (title.isEmpty() || description.isEmpty()) return

        recipesAdapter.addRecipe(
            RecipeUiModel(title, description, flavor)
        )
        main_recipe_title.setText("")
        main_recipe_description.setText("")
    }
}
