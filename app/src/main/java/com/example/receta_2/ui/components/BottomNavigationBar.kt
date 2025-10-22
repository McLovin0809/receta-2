package com.example.receta_2.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.receta_2.navigation.AppScreen

@Composable
fun AppBottomBar(
    navController: NavController,
    isLoggedIn: Boolean // Parámetro clave para decidir qué mostrar
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        // --- Ítem 1: Home (siempre visible) ---
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == AppScreen.Home.route,
            onClick = {
                navController.navigate(AppScreen.Home.route) {
                    // Limpia la pila hasta el inicio para no acumular pantallas Home
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    // Evita múltiples copias de Home
                    launchSingleTop = true
                    // Restaura el estado si se vuelve a la pantalla
                    restoreState = true
                }
            }
        )

        // --- Ítem 2: Login o Perfil (condicional) ---
        val destination = if (isLoggedIn) AppScreen.Profile.route else AppScreen.Login.route
        val label = if (isLoggedIn) "Perfil" else "Login"
        val selected = currentRoute == destination

        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = label) },
            label = { Text(label) },
            selected = selected,
            onClick = {
                navController.navigate(destination) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
