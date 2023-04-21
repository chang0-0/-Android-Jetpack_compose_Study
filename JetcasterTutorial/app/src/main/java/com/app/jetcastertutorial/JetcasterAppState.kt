package com.app.jetcastertutorial

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Player : Screen("plater/{episodeUri}") {
        fun createRoute(episodeUri: String) = "player/$episodeUri"
    } // End of Player
} // End of Screen class

@Composable
fun rememberJetcasterAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    JetcasterAppState(navController, context)
} // End of rememberJetcasterAppState

class JetcasterAppState(
    val navController: NavHostController,
    private val context: Context
) {

} // End of JetcasterAppState class

private fun NavBackStackEntry.lifecycleIsresumed() =
    this.getLifecycle().currentState == Lifecycle.State.RESUMED