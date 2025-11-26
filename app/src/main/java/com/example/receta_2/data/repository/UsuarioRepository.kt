package com.example.receta_2.data.repository

import com.example.receta_2.data.model.Usuario
import com.example.receta_2.data.remote.RetrofitInstance
import retrofit2.Response

class UsuarioRepository {

    suspend fun getUsuarios(): List<Usuario> {
        return RetrofitInstance.apiUsuario.getUsuarios()
    }

    suspend fun getUsuarioPorId(id: Int): Usuario {
        return RetrofitInstance.apiUsuario.getUsuarioPorId(id)
    }

    suspend fun getUsuarioPorEmail(email: String): Usuario {
        return RetrofitInstance.apiUsuario.getUsuarioPorEmail(email)
    }

    suspend fun crearUsuario(usuario: Usuario): Response<Usuario> {
        return RetrofitInstance.apiUsuario.crearUsuario(usuario)
    }

    suspend fun actualizarUsuario(id: Int, usuario: Usuario): Response<Usuario> {
        return RetrofitInstance.apiUsuario.actualizarUsuario(id, usuario)
    }

    suspend fun actualizarParcial(id: Int, cambios: Usuario): Response<Usuario> {
        return RetrofitInstance.apiUsuario.actualizarParcial(id, cambios)
    }

    suspend fun eliminarUsuario(id: Int): Response<Unit> {
        return RetrofitInstance.apiUsuario.eliminarUsuario(id)
    }

    // Métodos equivalentes a los personalizados de tu backend
    suspend fun existePorEmail(email: String): Boolean {
        val response = RetrofitInstance.apiUsuario.existePorEmail(email)
        return response.body() ?: false
    }


    suspend fun getUsuarioConRecetas(id: Int): Usuario? {
        val response = RetrofitInstance.apiUsuario.getUsuarioConRecetas(id)
        return if (response.isSuccessful) response.body() else null
    }

}