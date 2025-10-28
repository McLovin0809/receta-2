package com.example.receta_2.data.model

import androidx.room.TypeConverter

class DatabaseConverters {

    private val separator = "‚‽,"


    @TypeConverter
    fun fromStringList(value: String?): List<String> {
        if (value.isNullOrEmpty()) {
            return emptyList()
        }
        return value.split(separator)
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String {
        if (list.isNullOrEmpty()) {
            return ""
        }
        return list.joinToString(separator)
    }


    @TypeConverter
    fun fromCategoryGroup(value: String?): CategoryGroup? {
        return value?.let { CategoryGroup.valueOf(it) }
    }

    @TypeConverter
    fun toCategoryGroup(categoryGroup: CategoryGroup?): String? {
        return categoryGroup?.name
    }
}
