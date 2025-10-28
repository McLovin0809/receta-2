package com.example.receta_2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.receta_2.data.AppDatabase
import com.example.receta_2.data.model.Recipe
import com.example.receta_2.data.model.SearchCategory
import com.example.receta_2.data.model.allCategories // Importa los datos iniciales
import com.example.receta_2.data.model.sampleRecipes   // Importa los datos iniciales
import com.example.receta_2.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes = _recipes.asStateFlow()

    // Para la lista de todas las categorías
    private val _categories = MutableStateFlow<List<SearchCategory>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _currentRecipe = MutableStateFlow<Recipe?>(null)
    val currentRecipe = _currentRecipe.asStateFlow()

    init {
        val recipeDao = AppDatabase.getDatabase(
            context = application,
            initialRecipes = sampleRecipes,
            initialCategories = allCategories
        ).recipeDao()

        repository = RecipeRepository(recipeDao)
        loadAllRecipes()
        loadAllCategories()
    }

    private fun loadAllRecipes() {
        viewModelScope.launch {
            _recipes.value = repository.getAllRecipes()
        }
    }

    private fun loadAllCategories() {
        viewModelScope.launch {
            _categories.value = repository.getAllCategories()
        }
    }

    fun loadRecipeById(id: String) {
        _currentRecipe.value = null
        viewModelScope.launch {
            _currentRecipe.value = repository.getRecipeById(id)
        }
    }
}
