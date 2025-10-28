package com.example.receta_2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val image: Int,
    val recipeCount: Int,
    val group: CategoryGroup
)
