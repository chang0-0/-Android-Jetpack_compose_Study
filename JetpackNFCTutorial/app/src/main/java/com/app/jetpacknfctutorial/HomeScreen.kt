package com.app.jetpacknfctutorial

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

private const val TAG = "HomeScreen_싸피"

@Composable
fun HomeScreen(
    nfcViewModel: NfcViewModel = viewModel(LocalContext.current as ComponentActivity),
    // nfcViewModel: NfcViewModel = viewModel(),
    navController: NavController
) {
//    readNFCData(nfcViewModel, Intent())
//
//    val mainActivity = LocalContext.current as MainActivity
//    mainActivity.intent.extras
    val nfcData = nfcViewModel.nfcData.observeAsState()
    val nfcState by nfcViewModel.nfcState.collectAsState()


    nfcState.let {
        Log.d(TAG, "nfcData.value.toString().let: ${nfcState}")
        HomeContent(nfcState, navController = navController)

        LaunchedEffect(key1 = nfcState) {
            Log.d(TAG, "LaunchedEffect(key1 = nfcData.value.toString()) { : ${nfcState} ")

            if (nfcState == "NEW NFC DATA") {
                Log.d(TAG, "HomeScreen LaunchedEffect : NEW NFC DATA")
                navController.navigate(route = HomeScreen.Main.route)
            } else if (nfcState == "SECOND") {
                Log.d(TAG, "HomeScreen LaunchedEffect : SECOND SCREEN ")
                navController.navigate(route = HomeScreen.Second.route)
            }
        }
    }

    val state by remember {
        mutableStateOf(nfcViewModel.nfcData)
    }
} // End of HomeScreen


// StateHoisting
@Composable
fun HomeContent(nfcData: String, navController: NavController? = null) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "NFC 태그를 해주세요")
            Spacer(modifier = Modifier.padding(40.dp))
            CircularProgressIndicator()
        }
    }
} // End of HomeContent

@Composable
@Preview
fun HomeScreenPreview() {
    Surface(modifier = Modifier.background(color = androidx.compose.ui.graphics.Color.Black)) {
        HomeContent("Test")
    }
} // End of HomeContent
