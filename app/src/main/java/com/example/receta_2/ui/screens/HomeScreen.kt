package com.example.receta_2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
// ✅ 1. SE ELIMINA LA IMPORTACIÓN PROBLEMÁTICA
// import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.receta_2.data.model.CategoryGroup
import com.example.receta_2.data.model.SearchCategory
import com.example.receta_2.data.model.allCategories
import com.example.receta_2.ui.theme.Receta2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    isLoggedIn: Boolean,
    onProfileClick: () -> Unit,
    navController: NavController,
    onFavoritesClick: () -> Unit
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RecetApp", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineMedium) },
                actions = {
                    if (isLoggedIn) {
                        IconButton(onClick = onFavoritesClick) {
                            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favoritos")
                        }
                        IconButton(onClick = onProfileClick) {
                            Icon(imageVector = Icons.Default.Person, contentDescription = "Perfil")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Ej: Pastas, Airfryer, Tapas…") },
                leadingIcon = { Icon(Icons.Default.Search, "Buscar") },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Limpiar búsqueda")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
            CategoryTabs(navController = navController, searchQuery = searchQuery)
        }
    }
}

@Composable
fun CategoryTabs(navController: NavController, searchQuery: String) {
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val categoryGroups = CategoryGroup.values()

    Column {
        if (searchQuery.isBlank()) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 16.dp,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]), // ✅ Ahora funciona
                        height = 3.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                categoryGroups.forEachIndexed { index, group ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(stringResource(id = group.titleResId), fontWeight = FontWeight.SemiBold) }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        val categoriesToShow = if (searchQuery.isBlank()) {
            allCategories.filter { it.group == categoryGroups[selectedTabIndex] }
        } else {
            allCategories.filter { it.name.contains(searchQuery, ignoreCase = true) }
        }

        if (categoriesToShow.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
                Text(
                    "No se encontraron categorías para \"$searchQuery\"",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 120.dp),
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(categoriesToShow, key = { it.id }) { subCategory ->
                    CategoryCard(
                        category = subCategory,
                        onCategoryClick = {
                            navController.navigate("recipe_list/${it.id}/${it.name}")
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(category: SearchCategory, onCategoryClick: (SearchCategory) -> Unit) {
    Card(onClick = { onCategoryClick(category) }, shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Box(modifier = Modifier.aspectRatio(1f).clip(RoundedCornerShape(16.dp))) {
            Image(painter = painterResource(id = category.image), contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black), startY = 200f)))
            Text(
                text = "${category.recipeCount}",
                modifier = Modifier.align(Alignment.TopEnd).padding(8.dp).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8f), RoundedCornerShape(50)).padding(horizontal = 8.dp, vertical = 2.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = category.name,
                modifier = Modifier.align(Alignment.BottomStart).padding(12.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true, name = "HomeScreen Logged In")
@Composable
fun HomeScreenLoggedInPreview() {
    Receta2Theme {
        HomeScreen(isLoggedIn = true, onProfileClick = {}, onFavoritesClick = {}, navController = rememberNavController())
    }
}
