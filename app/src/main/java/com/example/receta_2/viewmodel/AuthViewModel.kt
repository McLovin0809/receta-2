package com.example.receta_2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    // _isLoggedIn es privado para que solo el ViewModel pueda cambiar su valor
    private val _isLoggedIn = MutableStateFlow(false)
    // isLoggedIn es público y de solo lectura (StateFlow) para que la UI lo observe
    val isLoggedIn = _isLoggedIn.asStateFlow()

    fun login() {
        viewModelScope.launch {
            // Aquí iría tu lógica real de inicio de sesión (llamada a API, etc.)
            // Por ahora, simplemente simulamos que el inicio de sesión fue exitoso.
            _isLoggedIn.value = true
        }
    }

    fun logout() {
        viewModelScope.launch {
            // Lógica para cerrar sesión
            _isLoggedIn.value = false
        }
    }
}
