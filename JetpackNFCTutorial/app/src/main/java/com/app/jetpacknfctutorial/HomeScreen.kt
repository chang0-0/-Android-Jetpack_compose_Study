package com.app.jetpacknfctutorial

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    if (nfcData.value == "NEW NFC DATA") {
        LaunchedEffect(key1 = nfcData) {

        }
    }

    // Hint 
    // LaunchedEffect를 사용해라
    // ViewModel의 생명주기 먹이기
    nfcData.value.let { nfcData ->
        HomeContent(nfcData = nfcData.toString())
    }



//    if (nfcData.toString() == "NEW NFC DATA") {
//        Log.d(TAG, "HomeScreen: ")
//        navController.navigate(route = HomeScreen.Main.route)
//    } else if (nfcData.toString() == "SECOND") {
//        navController.navigate(route = HomeScreen.Second.route)
//    }
} // End of HomeScreen


// StateHoisting
@Composable
fun HomeContent(nfcData: String = "") {
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
        HomeContent()
    }
} // End of HomeContent

//private fun readNFCData(nfcViewModel: NfcViewModel, intent: Intent) {
//    val message = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
//    if (message != null) {
//        message.forEach {
//            val ndef = it as NdefMessage
//
//            for (rec in ndef.records) {
////                    Log.d(TAG, "TNF  : ${rec.tnf}")
////                    Log.d(TAG, "ID   : ${String(rec.id)}")
////                    Log.d(TAG, "TYPE : ${String(rec.type)}")
////                    Log.d(TAG, "PLoad: ${String(rec.payload)}")
//
//                val strPload = String(rec.payload)
//
//                // nfcViewModel setNfcData
//                nfcViewModel.setNfcData(newNfcData = strPload.substring(3))
//
//                val type = String(rec.type)
//                when (type) {
//                    "T" -> {
//                        val payload = rec.payload
//                        Log.d(TAG, "processNFC type T : ${String(payload)}")
//                    }
//                    "U" -> {
//                        val uri = rec.toUri()
////                        startActivity(Intent().apply {
////                            setAction(Intent.ACTION_SENDTO)
////                            data = uri
////                            Log.d(TAG, "processNFC: $uri")
////                        })
//                    }
//                    "Sp" -> {
//                        Log.d(TAG, "processNFC: ${String(rec.type)}")
//                    }
//                }
//            }
//        }
//    }
//} // End of readNFCData
