package com.example.receta_2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.receta_2.navigation.BottomBar
import com.example.receta_2.navigation.BottomNavItem
import com.example.receta_2.navigation.Routes
import com.example.receta_2.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Perfil") }) }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Pantalla de perfil")
        }
    }
}
@Composable
fun App() {
    val navController = rememberNavController()
    val bottomItems = listOf(BottomNavItem.Home, BottomNavItem.Profile)

    Scaffold(
        bottomBar = { BottomBar(navController, bottomItems) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) {
                val vm: MainViewModel = viewModel()
                HomeScreen(viewModel = vm, onItemClick = { id ->
                    navController.navigate(Routes.detailRoute(id))
                })
            }
            composable(Routes.PROFILE) { ProfileScreen() }
            composable(
                route = Routes.DETAIL,
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { backStackEntry ->
                val vm: MainViewModel = viewModel()
                val id = backStackEntry.arguments?.getInt("itemId") ?: -1
                DetailScreen(itemId = id, viewModel = vm, onBack = { navController.popBackStack() })
            }
        }
    }
}

