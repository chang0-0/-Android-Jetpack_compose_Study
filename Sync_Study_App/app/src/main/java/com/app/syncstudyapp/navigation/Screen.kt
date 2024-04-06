package com.app.syncstudyapp.navigation

sealed class Screen(val route: String) {
    data object Main : Screen(route = "main_screen")
    data object Home : Screen(route = "home_screen")
} // End of Screen class