package com.example.receta_2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#\$%^&+=!]{6,}$")

    val isEmailValid = emailRegex.matches(email)
    val isPasswordValid = passwordRegex.matches(password)
    val isFormValid = email.isNotBlank() && password.isNotBlank() && isEmailValid && isPasswordValid

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            isError = email.isNotEmpty() && !isEmailValid,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        if (!isEmailValid && email.isNotEmpty()) {
            Text("Correo inválido. Usa formato usuario@dominio.cl", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            isError = password.isNotEmpty() && !isPasswordValid,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        if (!isPasswordValid && password.isNotEmpty()) {
            Text("Debe tener al menos 6 caracteres, incluir letras y números.", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(Modifier.height(32.dp))

        Button(onClick = onLoginSuccess, enabled = isFormValid, modifier = Modifier.fillMaxWidth()) {
            Text("Entrar")
        }

        Spacer(Modifier.height(16.dp))
        TextButton(onClick = onRegisterClick) { Text("¿No tienes cuenta? Regístrate") }
    }
}
