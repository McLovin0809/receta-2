package com.example.receta_2.repository

import com.example.receta_2.data.dao.RecipeDao
import com.example.receta_2.data.model.toDomain
import com.example.receta_2.data.model.Recipe
import com.example.receta_2.data.model.SearchCategory


class RecipeRepository(private val recipeDao: RecipeDao) {


    suspend fun getAllRecipes(): List<Recipe> {
        val recipeEntities = recipeDao.getAllRecipes()
        return recipeEntities.map { it.toDomain() }
    }

    suspend fun getRecipeById(id: String): Recipe? {
        val recipeEntity = recipeDao.getRecipeById(id)
        // Mapea solo si no es nulo
        return recipeEntity?.toDomain()
    }


    suspend fun getAllCategories(): List<SearchCategory> {
        val categoryEntities = recipeDao.getAllCategories()
        return categoryEntities.map { it.toDomain() }
    }
}
