package com.example.receta_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.receta_2.navigation.AppBottomBar
import com.example.receta_2.navigation.AppScreen
import com.example.receta_2.navigation.ExtraRoutes
import com.example.receta_2.data.model.sampleRecipes
import com.example.receta_2.ui.screens.*
import com.example.receta_2.ui.theme.Receta2Theme
import com.example.receta_2.viewmodel.AuthViewModel
import com.example.receta_2.viewmodel.FavoritesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Receta2Theme {
                val authViewModel: AuthViewModel = viewModel()
                val favoritesViewModel: FavoritesViewModel = viewModel()
                AppNavigator(authViewModel = authViewModel, favoritesViewModel = favoritesViewModel)
            }
        }
    }
}

@Composable
fun AppNavigator(authViewModel: AuthViewModel, favoritesViewModel: FavoritesViewModel) {
    val navController = rememberNavController()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    Scaffold(bottomBar = { AppBottomBar(navController = navController, isLoggedIn = isLoggedIn) }) { innerPadding ->
        AppNavHost(modifier = Modifier.padding(innerPadding), navController = navController, authViewModel = authViewModel, favoritesViewModel = favoritesViewModel)
    }
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController, authViewModel: AuthViewModel, favoritesViewModel: FavoritesViewModel) {
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    NavHost(modifier = modifier, navController = navController, startDestination = AppScreen.Home.route) {
        composable(AppScreen.Home.route) {
            HomeScreen(isLoggedIn = isLoggedIn, navController = navController, onProfileClick = { navController.navigate(AppScreen.Profile.route) }, onFavoritesClick = { navController.navigate("favorites") })
        }
        composable("favorites") {
            if (isLoggedIn) {
                FavoritesScreen(navController = navController, favoritesViewModel = favoritesViewModel)
            } else {
                LaunchedEffect(Unit) {
                    navController.navigate(AppScreen.Login.route) { popUpTo("favorites") { inclusive = true } }
                }
            }
        }
        composable(AppScreen.Profile.route) {
            if (isLoggedIn) {
                ProfileScreen(onSettingsClick = { navController.navigate(ExtraRoutes.SETTINGS) }, onLogoutClick = {
                    authViewModel.logout()
                    navController.navigate(AppScreen.Home.route) { popUpTo(navController.graph.id) { inclusive = true } }
                })
            } else {
                LaunchedEffect(Unit) {
                    navController.navigate(AppScreen.Login.route) { popUpTo(AppScreen.Profile.route) { inclusive = true } }
                }
            }
        }
        composable(route = "recipe_list/{categoryId}/{categoryName}", arguments = listOf(navArgument("categoryId") { type = NavType.StringType }, navArgument("categoryName") { type = NavType.StringType })) { backStackEntry ->
            RecipeListScreen(navController = navController, categoryId = backStackEntry.arguments?.getString("categoryId"), categoryName = backStackEntry.arguments?.getString("categoryName"), favoritesViewModel = favoritesViewModel, isLoggedIn = isLoggedIn)
        }
        composable(route = "recipe_detail/{recipeId}", arguments = listOf(navArgument("recipeId") { type = NavType.StringType })) { backStackEntry ->
            val recipe = sampleRecipes.find { it.id == backStackEntry.arguments?.getString("recipeId") }
            if (recipe != null) {
                RecipeDetailScreen(navController = navController, recipe = recipe)
            }
        }
        composable(AppScreen.Login.route) {
            LoginScreen(onLoginSuccess = {
                authViewModel.login()
                navController.navigate(AppScreen.Home.route) { popUpTo(navController.graph.id) { inclusive = true } }
            }, onRegisterClick = { navController.navigate(ExtraRoutes.REGISTER) })
        }
        composable(ExtraRoutes.REGISTER) {
            RegisterScreen(navController = navController, onRegisterSuccess = {
                navController.navigate(AppScreen.Login.route) { popUpTo(AppScreen.Login.route) { inclusive = true } }
            })
        }
        composable(ExtraRoutes.SETTINGS) {
            SettingsScreen()
        }
    }
}
