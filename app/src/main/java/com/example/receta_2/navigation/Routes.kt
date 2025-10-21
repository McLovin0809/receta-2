package com.example.receta_2.navigation

object Routes {
    const val HOME = "home"
    const val PROFILE = "profile"
    const val DETAIL = "detail/{itenId}"
    fun detailRoute(itemId: Int) = "detail/$itemId"
}