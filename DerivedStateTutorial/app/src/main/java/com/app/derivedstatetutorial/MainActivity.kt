package com.app.derivedstatetutorial

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.derivedstatetutorial.ui.theme.DerivedStateTutorialTheme

private const val TAG = "MainActivity_싸피"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DerivedStateTutorialTheme() {
                TestScreen()
            }
        }
    } // End of onCreate
} // End of Main class


@Composable
fun TestScreen() {
    var count by remember { mutableStateOf(0) }
    val calculation by remember {
        derivedStateOf {
            Log.d(TAG, "Calculated")
            count > 3
        }
    }

    Log.d(TAG, "READ $calculation")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            Log.d(TAG, "Clicked")
            count += 1
        }) {
            Text(text = "Increment")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "count : ${count}"
        )
    }
} // End of TestScreen
