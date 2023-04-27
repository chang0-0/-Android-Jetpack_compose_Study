package com.app.composetopappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.composetopappbar.ui.theme.ComposeTopappbarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTopappbarTheme {
                MainScreen()
            }
        }
    }
}
