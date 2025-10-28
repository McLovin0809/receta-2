// En: com/example/receta_2/data/model/RecipeEntity.kt
package com.example.receta_2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val image: Int, // Room puede almacenar tipos primitivos como Int
    val categoryIds: List<String>, // Necesitará un TypeConverter
    val ingredients: List<String>, // Necesitará un TypeConverter
    val steps: List<String>        // Necesitará un TypeConverter
)
