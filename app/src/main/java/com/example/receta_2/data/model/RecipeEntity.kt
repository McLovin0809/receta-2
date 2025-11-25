package com.example.receta_2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val image: String,
    val categoryIds: List<String>,
    val ingredients: List<String>,
    val steps: List<String>
)
