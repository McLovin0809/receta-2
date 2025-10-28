package com.example.receta_2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.receta_2.ui.components.RecipeItemCard
import com.example.receta_2.data.model.sampleRecipes
import com.example.receta_2.viewmodel.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    navController: NavController,
    categoryId: String?,
    categoryName: String?,
    favoritesViewModel: FavoritesViewModel,
    isLoggedIn: Boolean
) {
    val recipesToShow = sampleRecipes.filter { it.categoryIds.contains(categoryId) }
    val favoriteIds by favoritesViewModel.favoriteRecipeIds.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryName ?: "Recetas") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (recipesToShow.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Aún no hay recetas en esta categoría.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(recipesToShow, key = { it.id }) { recipe ->
                    RecipeItemCard(
                        recipe = recipe,
                        isFavorite = favoriteIds.contains(recipe.id),
                        onToggleFavorite = { favoritesViewModel.toggleFavorite(recipe.id) },
                        onDetailsClick = { navController.navigate("recipe_detail/${recipe.id}") },
                        isLoggedIn = isLoggedIn
                    )
                }
            }
        }
    }
}

