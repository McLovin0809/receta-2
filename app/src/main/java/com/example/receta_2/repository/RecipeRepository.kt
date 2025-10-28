// En: com/example/receta_2/data/repository/RecipeRepository.kt
package com.example.receta_2.data.repository

import com.example.receta_2.data.dao.RecipeDao
// La importación ahora funcionará porque Mappers.kt existe
import com.example.receta_2.data.model.toDomain
import com.example.receta_2.data.model.Recipe
import com.example.receta_2.data.model.SearchCategory

/**
 * El Repositorio gestiona el acceso a los datos.
 * Es el único lugar que debería comunicarse directamente con el DAO.
 */
class RecipeRepository(private val recipeDao: RecipeDao) {

    /**
     * Obtiene todas las recetas de la base de datos y las convierte
     * al modelo de dominio/UI (Recipe) antes de devolverlas.
     */
    suspend fun getAllRecipes(): List<Recipe> {
        val recipeEntities = recipeDao.getAllRecipes()
        // Mapea cada entidad al modelo de dominio usando la función de extensión
        return recipeEntities.map { it.toDomain() }
    }

    /**
     * Obtiene una receta por su ID y la convierte al modelo de dominio.
     */
    suspend fun getRecipeById(id: String): Recipe? {
        val recipeEntity = recipeDao.getRecipeById(id)
        // Mapea solo si no es nulo
        return recipeEntity?.toDomain()
    }

    /**
     * Obtiene todas las categorías y las convierte al modelo de dominio.
     */
    suspend fun getAllCategories(): List<SearchCategory> {
        val categoryEntities = recipeDao.getAllCategories()
        return categoryEntities.map { it.toDomain() }
    }
}
