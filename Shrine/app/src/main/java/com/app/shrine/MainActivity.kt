package com.app.shrine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.shrine.ui.theme.ShrineTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShrineTheme {
                Backdrop()
            }
        }
    } // End of onCreate
} // End of MainActivity class


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShrineTheme {
        Cart()
    }
} // End of DefaultPreview
