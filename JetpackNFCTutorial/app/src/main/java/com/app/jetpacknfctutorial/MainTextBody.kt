package com.app.jetpacknfctutorial

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.nfc.NfcAdapter
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

private lateinit var nAdapter: NfcAdapter
private lateinit var pendingIntent: PendingIntent
private const val TAG = "MainTextBody_싸피"
// private val nfcViewModel by viewModels<NfcViewModel>()

@Composable
fun MainScreen(nfcViewModel: NfcViewModel = viewModel(), navController: NavHostController) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val lastestLifeCycleEvent = remember {
        mutableStateOf(Lifecycle.Event.ON_ANY)
    }

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            lastestLifeCycleEvent.value = event
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    MainTextBody(
        navController,
        "",
    )
} // End of MainScreen

@Composable
fun MainTextBody(
    navController: NavController,
    nfcData: String,
) {
    val context = LocalContext.current
    val activity = context.findActivity()

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //nfcRead(LocalContext.current)
        Text(text = "test", fontSize = MaterialTheme.typography.h3.fontSize, color = Color.Black)
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = {
            navController.navigate(route = HomeScreen.Second.route)
            Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Change Screen Button")
        }
        Text(text = nfcData)
    }
} // End of MainTextScreen


@Composable
fun rememberLifecycleEvent(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current): Lifecycle.Event {
    var state by remember {
        mutableStateOf(Lifecycle.Event.ON_ANY)
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            state = event
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    return state
} // End of rememberLifecycleEvent


private fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
} // End of Context.findActivity
