package com.nassafy.nestednavigationtuto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nassafy.nestednavigationtuto.ui.theme.NestedNavigationTutoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NestedNavigationTutoTheme {

            }
        }
    } // End of onCreate
} // End of MainActivity class
