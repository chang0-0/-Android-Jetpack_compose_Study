package com.app.syncstudyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.syncstudyapp.navigation.SetUpNavGraph
import com.app.syncstudyapp.ui.theme.SyncStudyAppTheme


private const val TAG = "MainActivity_창영"

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SyncStudyAppTheme {
                navController = rememberNavController()
                SetUpNavGraph()
            }
        }
    } // End of onCreate()
} // End of MainActivity class
