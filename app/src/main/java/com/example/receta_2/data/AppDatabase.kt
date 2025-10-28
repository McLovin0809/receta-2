// En: com/example/receta_2/data/AppDatabase.kt
package com.example.receta_2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.receta_2.data.dao.RecipeDao
import com.example.receta_2.data.model.Recipe
import com.example.receta_2.data.model.SearchCategory
import com.example.receta_2.data.model.CategoryEntity
import com.example.receta_2.data.model.DatabaseConverters
import com.example.receta_2.data.model.RecipeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [RecipeEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // MODIFICACIÓN 1: Ahora aceptamos las listas de datos iniciales aquí
        fun getDatabase(
            context: Context,
            initialRecipes: List<Recipe>,
            initialCategories: List<SearchCategory>
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "recipe_app_database"
                )
                    // MODIFICACIÓN 2: Pasamos las listas al Callback
                    .addCallback(DatabaseCallback(initialRecipes, initialCategories))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    // MODIFICACIÓN 3: El Callback ahora recibe los datos en su constructor
    private class DatabaseCallback(
        private val initialRecipes: List<Recipe>,
        private val initialCategories: List<SearchCategory>
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.recipeDao())
                }
            }
        }

        suspend fun populateDatabase(recipeDao: RecipeDao) {
            // MODIFICACIÓN 4: Usamos las listas recibidas en el constructor
            val categoryEntities = initialCategories.map { category ->
                CategoryEntity(category.id, category.name, category.image, category.recipeCount, category.group)
            }
            recipeDao.insertAllCategories(categoryEntities)

            val recipeEntities = initialRecipes.map { recipe ->
                RecipeEntity(recipe.id, recipe.name, recipe.description, recipe.image, recipe.categoryIds, recipe.ingredients, recipe.steps)
            }
            recipeDao.insertAllRecipes(recipeEntities)
        }
    }
}
