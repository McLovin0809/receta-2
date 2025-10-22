package com.example.receta_2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.receta_2.data.model.Recipe

// El composable principal para la pantalla de detalle
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    navController: NavController,
    recipe: Recipe // Recibe el objeto Recipe completo
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe.name, maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        // Hacemos que toda la pantalla sea "scrolleable"
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Imagen principal de la receta
            Image(
                painter = painterResource(id = recipe.image),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            // Contenido de la receta (título, descripción, etc.)
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodyLarge
                )

                Divider(modifier = Modifier.padding(vertical = 24.dp))

                // --- Sección de Ingredientes ---
                Text(
                    text = "Ingredientes",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    recipe.ingredients.forEach { ingredient ->
                        IngredientItem(text = ingredient)
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 24.dp))

                // --- Sección de Pasos ---
                Text(
                    text = "Preparación",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    recipe.steps.forEachIndexed { index, step ->
                        StepItem(stepNumber = index + 1, stepText = step)
                    }
                }
            }
        }
    }
}

// Composable para un solo ítem de la lista de ingredientes
@Composable
fun IngredientItem(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // Un punto visual para la viñeta
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

// Composable para un solo paso de la preparación, con numeración
@Composable
fun StepItem(stepNumber: Int, stepText: String) {
    Row(verticalAlignment = Alignment.Top) {
        // Número del paso
        Text(
            text = "$stepNumber.",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Descripción del paso
        Text(
            text = stepText,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 2.dp) // Alineación fina
        )
    }
}
