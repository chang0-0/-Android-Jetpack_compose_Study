package com.app.quotes_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.quotes_app.screens.QuoteDetail
import com.app.quotes_app.ui.theme.Quotes_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Quotes_AppTheme {
                QuoteDetail()
            }
        }
    } // End of onCreate
} // End of MainAcitivity class

