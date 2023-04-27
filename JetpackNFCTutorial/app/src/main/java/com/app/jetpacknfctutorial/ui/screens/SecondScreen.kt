package com.app.jetpacknfctutorial.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.app.jetpacknfctutorial.HomeScreen

@Composable
fun SecondScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Second Screen",
            modifier = Modifier.clickable {
                navController.navigate(HomeScreen.Home.route) {
                    popUpTo(HomeScreen.Home.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
} // End of secondScreen
