package com.example.receta_2.data.model

fun Recipe.toEntity(): RecipeEntity {
    return RecipeEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.image,
        categoryIds = this.categoryIds,
        ingredients = this.ingredients,
        steps = this.steps
    )
}

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
