package com.example.receta_2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.receta_2.data.dao.RecipeDao
import com.example.receta_2.data.model.CategoryEntity
import com.example.receta_2.data.model.DatabaseConverters
import com.example.receta_2.data.model.Recipe
import com.example.receta_2.data.model.RecipeEntity
import com.example.receta_2.data.model.SearchCategory
import com.example.receta_2.data.model.toEntity
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
                    .addCallback(DatabaseCallback(initialRecipes, initialCategories))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

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
            recipeDao.deleteAllCategories()
            recipeDao.deleteAllRecipes()


            val categoryEntities = initialCategories.map { category ->
                CategoryEntity(
                    id = category.id,
                    name = category.name,
                    image = category.image,
                    recipeCount = category.recipeCount,
                    group = category.group
                )
            }
            recipeDao.insertAllCategories(categoryEntities)

            val recipeEntities = initialRecipes.map { it.toEntity() }
            recipeDao.insertAllRecipes(recipeEntities)
        }
    }
}
