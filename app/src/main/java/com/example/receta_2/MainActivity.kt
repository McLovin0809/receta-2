package com.example.receta_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.receta_2.ui.screens.HomeScreen
import com.example.receta_2.ui.theme.Receta2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Receta2Theme {
                HomeScreen()
            }
        }
    }
}
