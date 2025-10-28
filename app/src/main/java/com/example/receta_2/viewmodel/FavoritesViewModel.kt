package com.example.receta_2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.receta_2.data.model.Recipe
import com.example.receta_2.data.model.sampleRecipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FavoritesViewModel : ViewModel() {

    private val _favoriteRecipeIds = MutableStateFlow<Set<String>>(emptySet())
    val favoriteRecipeIds = _favoriteRecipeIds.asStateFlow()

    private val _favoriteRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val favoriteRecipes = _favoriteRecipes.asStateFlow()

    fun isFavorite(recipeId: String): Boolean {
        return _favoriteRecipeIds.value.contains(recipeId)
    }

    fun toggleFavorite(recipeId: String) {
        _favoriteRecipeIds.update { currentIds ->
            val newIds = currentIds.toMutableSet()
            if (newIds.contains(recipeId)) {
                newIds.remove(recipeId)
            } else {
                newIds.add(recipeId)
            }
            newIds
        }
        updateFavoriteRecipes()
    }

    private fun updateFavoriteRecipes() {
        _favoriteRecipes.value = sampleRecipes.filter {
            _favoriteRecipeIds.value.contains(it.id)
        }
    }
}
