package com.app.shrine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.shrine.ui.theme.ShrineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShrineTheme {
                Cart()
            }
        }
    } // End of onCreate
} // End of MainActivity class

@Composable
fun Greeting(name: String) {
    Column {
        Text(text = "Hello $name!")
        // 패딩 추가
        Spacer(Modifier.height(32.dp))
        Text(text = "This is a subtitle")
    }
} // End of Greeting

@Preview(name = "Live frame", showBackground = true)
@Composable
fun DefaultPreview() {
    ShrineTheme {
        Greeting("Android")
    }
} // End of DefaultPreview