package com.app.jetpacknfctutorial.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.jetpacknfctutorial.HomeScreen
import com.app.jetpacknfctutorial.MainScreen
import com.app.jetpacknfctutorial.ui.screens.SecondScreen


@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen.Home.route
    ) {
        composable(
            route = HomeScreen.Home.route
        ) {
            HomeScreen(
                navController = navController
            )
        }
        composable(
            route = HomeScreen.Main.route
        ) {
            MainScreen(navController = navController)
        }
        composable(
            route = HomeScreen.Second.route
        ) {
            SecondScreen(
                navController = navController
            )
        }
    }
} // End of NavGraphBuilder
