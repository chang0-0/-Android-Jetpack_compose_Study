package com.app.sideeffecttutorial

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.app.sideeffecttutorial.ui.theme.SideEffectTutorialTheme
import kotlinx.coroutines.delay


private const val TAG = "MainActivity_싸피"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SideEffectTutorialTheme {
                App()
            }
        }
    } // End of onCreate
} // End of MainActivity class

fun a() {
    Log.d(TAG, "a: I am A from App")
} // End of a

fun b() {
    Log.d(TAG, "b: I am B from App")
} // End of b


@Composable
fun App() {
//    var counter = remember {
//        mutableStateOf(0)
//    }
//    LaunchedEffect(key1 = Unit) {
//        delay(2000)
//        counter.value = 10
//    }
//    Counter(counter.value)

    var state = remember {
        mutableStateOf(::a)
    }

    Button(onClick = {
        state.value = ::b
    }) {
        Text(text = "Click to change state")
    }

    LandingScreen {
        state.value
    }
} // End of App

@Composable
fun Counter(value: Int) {
    val state = rememberUpdatedState(newValue = value)

    LaunchedEffect(key1 = Unit) {
        delay(5000)
        Log.d(TAG, "${state.value}")
    }
    Text(text = value.toString())
} // End of Counter

@Composable
fun LandingScreen(onTimeout: () -> Unit) {
    val currentOnTimeOut by rememberUpdatedState(onTimeout)

    LaunchedEffect(true) {
        delay(5000)
        currentOnTimeOut()
    }
} // End of LandingScreen