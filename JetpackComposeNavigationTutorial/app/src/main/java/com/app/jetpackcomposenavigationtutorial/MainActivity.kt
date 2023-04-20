package com.app.jetpackcomposenavigationtutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.jetpackcomposenavigationtutorial.ui.theme.JetpackComposeNavigationTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeNavigationTutorialTheme {
                Navigation()
            }
        }
    } // End of onCreate()
} // End of MainActivity class
