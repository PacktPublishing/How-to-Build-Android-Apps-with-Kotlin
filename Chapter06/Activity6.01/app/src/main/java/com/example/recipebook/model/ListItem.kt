package com.example.recipebook.model

interface ListItem

data class TitleUiModel(
    val title: String
) : ListItem

data class RecipeUiModel(
    val title: String,
    val description: String,
    val flavor: Flavor
) : ListItem

enum class Flavor {
    SAVORY,
    SWEET
}
