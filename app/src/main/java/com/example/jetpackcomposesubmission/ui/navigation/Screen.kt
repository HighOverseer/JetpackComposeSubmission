package com.example.jetpackcomposesubmission.ui.navigation

sealed class Screen(
    val route: String
) {
    object Home : Screen("home")
    object Detail : Screen("home/{userId}") {
        fun createRoute(userId: Long) = "home/$userId"
    }

    object About : Screen("screen")
}