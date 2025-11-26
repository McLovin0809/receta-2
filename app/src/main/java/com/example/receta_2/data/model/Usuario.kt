package com.example.receta_2.data.model

data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String,
    val password: String,
    val recetas: List<Receta> = emptyList()
)