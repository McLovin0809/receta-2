package com.example.receta_2.data.model

import androidx.annotation.DrawableRes

data class SearchCategory(
    val id: String,
    val name: String,
    @DrawableRes val image: Int,
    val recipeCount: Int,
    val group: CategoryGroup
)

data class Recipe(
    val id: String,
    val name: String,
    val description: String,
    @DrawableRes val image: Int,
    val categoryIds: List<String>,
    val ingredients: List<String>,
    val steps: List<String>
)

fun RecipeEntity.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.image,
        categoryIds = this.categoryIds,
        ingredients = this.ingredients,
        steps = this.steps
    )
}


fun CategoryEntity.toDomain(): SearchCategory {
    return SearchCategory(
        id = this.id,
        name = this.name,
        image = this.image,
        recipeCount = this.recipeCount,
        group = this.group
    )
}
