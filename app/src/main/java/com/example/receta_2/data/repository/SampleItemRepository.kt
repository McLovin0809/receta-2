package com.example.receta_2.data.repository

import com.example.receta_2.data.model.Item

class SampleRepository {
    private val items = List(20) { index ->
        Item(
            id = index,
            name = "Item #$index",
            description = "Descripción detallada del item $index."
        )
    }

    fun getAll(): List<Item> = items
    fun getById(id: Int): Item? = items.find { it.id == id }
}