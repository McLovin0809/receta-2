package com.example.receta_2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.MenuBook // Icono para "Recetas"
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.receta_2.navigation.AppScreen

@Composable
fun AppBottomBar(
    navController: NavController,
    isLoggedIn: Boolean
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        if (isLoggedIn) {

            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                selected = currentRoute == AppScreen.Home.route,
                onClick = {
                    navController.navigate(AppScreen.Home.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )


        } else {

            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                selected = currentRoute == AppScreen.Home.route,
                onClick = {
                    navController.navigate(AppScreen.Home.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )

            NavigationBarItem(
                icon = { Icon(Icons.Filled.LockOpen, contentDescription = "Login") },
                label = { Text("Login") },
                selected = currentRoute == AppScreen.Login.route,
                onClick = {
                    navController.navigate(AppScreen.Login.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
