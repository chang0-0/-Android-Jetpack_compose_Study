package com.app.jetcastertutorial

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature


@Composable
fun JetcasterApp(
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    appState : JetcasterAppState = rememberJetcasterAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            navBackStackEntry ->


        }
    }
} // End of JetcasterApp
