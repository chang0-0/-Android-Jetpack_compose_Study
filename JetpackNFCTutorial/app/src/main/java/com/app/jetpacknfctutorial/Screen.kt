package com.app.jetpacknfctutorial

sealed class HomeScreen(val route: String) {
    object Home : HomeScreen(route = "home_screen")
    object Main : HomeScreen(route = "main_screen")
    object Second : HomeScreen(route = "detail_screen")
} // End of Screen class
