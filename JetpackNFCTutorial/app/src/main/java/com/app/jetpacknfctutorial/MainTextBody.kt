package com.app.jetpacknfctutorial

import android.app.PendingIntent
import android.nfc.NfcAdapter
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberUpdatedState
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
fun MainScreen(
    nfcViewModel: NfcViewModel = viewModel(LocalContext.current as ComponentActivity),
    navController: NavHostController
) {
    val nfcValue = nfcViewModel.nfcData.observeAsState()
    MainTextBody(navController = navController, nfcData = nfcValue.value.toString())

    OnLifecycleEvent { owner, event ->
        // do stuff on event
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                Log.d(TAG, "MainScreen: ON_CREATE ")
            }
            Lifecycle.Event.ON_START -> {
                Log.d(TAG, "MainScreen: ON_START ")
            }
            Lifecycle.Event.ON_RESUME -> {
                Log.d(TAG, "MainScreen: ON_RESUME ")
            }
            Lifecycle.Event.ON_PAUSE -> {
                Log.d(TAG, "MainScreen: ON_PAUSE")
            }
            Lifecycle.Event.ON_STOP -> {
                Log.d(TAG, "MainScreen: ON_STOP")
            }
            Lifecycle.Event.ON_DESTROY -> {
                Log.d(TAG, "MainScreen: ON_DESTROY")
            }
            else -> return@OnLifecycleEvent
        }
    }

} // End of MainScreen


@Composable
fun MainTextBody(
    navController: NavController,
    nfcData: String,
) {
    val context = LocalContext.current

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
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}