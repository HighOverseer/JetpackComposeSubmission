package com.example.jetpackcomposesubmission.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposesubmission.ui.navigation.Screen
import com.example.jetpackcomposesubmission.ui.screen.about.AboutScreen
import com.example.jetpackcomposesubmission.ui.screen.detail.DetailScreen
import com.example.jetpackcomposesubmission.ui.screen.home.HomeScreen

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToDetail = { userId ->
                    navController.navigate(Screen.Detail.createRoute(userId))
                },
                navigateToAbout = {
                    navController.navigate(Screen.About.route)
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.LongType
                }
            )
        ) {
            val userId = it.arguments?.getLong("userId") ?: -1L
            DetailScreen(
                selectedUserId = userId,
                navigateBackToHome = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            route = Screen.About.route
        ) {
            AboutScreen(
                navigateBackToHome = {
                    navController.navigateUp()
                }
            )
        }
    }
}

fun getToast(context: Context, message: String): Toast {
    return Toast.makeText(context, message, Toast.LENGTH_SHORT)
}