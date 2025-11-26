package com.example.receta_2.data.remote

import com.example.receta_2.data.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
interface UsuarioApiService {

    @GET("api/usuarios")
    suspend fun getUsuarios(): List<Usuario>

    @GET("api/usuarios/{id}")
    suspend fun getUsuarioPorId(@Path("id") id: Int): Usuario

    @GET("api/usuarios/email/{email}")
    suspend fun getUsuarioPorEmail(@Path("email") email: String): Usuario

    @GET("api/usuarios/existe/{email}")
    suspend fun existePorEmail(@Path("email") email: String): Response<Boolean>
    @GET("api/usuarios/con-recetas/{id}")
    suspend fun getUsuarioConRecetas(@Path("id") id: Int): Response<Usuario>
    @POST("api/usuarios")
    suspend fun crearUsuario(@Body usuario: Usuario): Response<Usuario>

    @PUT("api/usuarios/{id}")
    suspend fun actualizarUsuario(@Path("id") id: Int, @Body usuario: Usuario): Response<Usuario>

    @PATCH("api/usuarios/{id}")
    suspend fun actualizarParcial(@Path("id") id: Int, @Body cambios: Usuario): Response<Usuario>

    @DELETE("api/usuarios/{id}")
    suspend fun eliminarUsuario(@Path("id") id: Int): Response<Unit>

}