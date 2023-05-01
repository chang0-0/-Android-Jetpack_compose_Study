package com.app.jetpacknfctutorial

import android.app.PendingIntent
import android.nfc.NfcAdapter
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

private lateinit var nAdapter: NfcAdapter
private lateinit var pendingIntent: PendingIntent
private const val TAG = "MainTextBody_싸피"
// private val nfcViewModel by viewModels<NfcViewModel>()

@Composable
fun MainScreen(nfcViewModel: NfcViewModel = viewModel(), navController: NavHostController) {
    val currentNfcValue = nfcViewModel.nfcData.observeAsState("")
    
    currentNfcValue.value.let { nfcValue ->
        Surface {
            MainTextBody(navController = navController, nfcData = nfcValue)
            NFCText(currentNfcValue.value)
            // Text(text = nfcViewModel.viewState.value)
        }
    }

    val nfcData = nfcViewModel.nfcLiveData.observeAsState()

    nfcData.value.let {
        Column {
            Text(text = "NFC 테스트 코드")
            nfcData.value?.let { it ->
                Text(text = it)
            }
        }
    }

} // End of MainScreen


@Composable
fun NFCText(value: String) {
    val state = rememberUpdatedState(newValue = value)
    Log.d(TAG, "NFCText: ${state.value}")
    Log.d(TAG, "NFCText: $value")

    Text(text = value)
    Text(text = state.value.toString())
} // End of NFCText

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
