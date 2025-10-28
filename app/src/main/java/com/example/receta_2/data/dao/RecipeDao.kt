package com.example.receta_2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.receta_2.data.model.CategoryEntity
import com.example.receta_2.data.model.RecipeEntity

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAllRecipes(recipes: List<RecipeEntity>)

    @Query("SELECT * FROM recipes ORDER BY name ASC")
    suspend fun getAllRecipes(): List<RecipeEntity>

    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: String): RecipeEntity?

    @Query("SELECT * FROM recipes WHERE name LIKE '%' || :query || '%'")
    suspend fun searchRecipesByName(query: String): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAllCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM categories ORDER BY name ASC")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE `group` = :categoryGroup")
    suspend fun getCategoriesByGroup(categoryGroup: String): List<CategoryEntity>
}