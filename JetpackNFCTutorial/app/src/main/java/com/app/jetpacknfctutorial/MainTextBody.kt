package com.app.jetpacknfctutorial

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
    val nfcPayload by nfcViewModel.nfcData.observeAsState("")
    //Log.d(TAG, "nfcPayload: $nfcPayload")

    MainTextBody(
        navController = navController,
        nfcPayload,
        nfcDataOnChange = {
            nfcViewModel.setNfcPayLoad(it)
        })
} // End of MainScreen

@Composable
fun MainTextBody(
    navController: NavController,
    nfcData: String,
    nfcDataOnChange: (String) -> Unit
) {
    val context = LocalContext.current
    val activity = context.findActivity()
    nAdapter = NfcAdapter.getDefaultAdapter(activity)
    val intent = Intent(context, MainActivity::class.java)
    pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE)

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //nfcRead(LocalContext.current)
        Text(text = "test", fontSize = MaterialTheme.typography.h3.fontSize, color = Color.Black)
        GetNfcData(
            intent
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = {
            navController.navigate(route = HomeScreen.Second.route)
            Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Change Screen Button")
        }
        Text(text = nfcData)
        Text(text = nfcDataOnChange.toString())
    }
} // End of MainTextScreen

private fun nfcRead(context: Context) {
    nAdapter = NfcAdapter.getDefaultAdapter(context)

    val i = Intent(
        context,
        MainActivity::class.java
    )

    i.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_MUTABLE)

    val main = context as MainActivity
} // End of nfcRead

@Composable
private fun GetNfcData(intent: Intent) {
    val action = intent.action
    Log.d(TAG, "getNfcData: $action")

    if (action == NfcAdapter.ACTION_NDEF_DISCOVERED || action == NfcAdapter.ACTION_TECH_DISCOVERED || action == NfcAdapter.ACTION_TAG_DISCOVERED) {
        intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)!!.takeWhile {
            val msg = it as NdefMessage
            val context = LocalContext as Activity

            for (record in msg.records) {
                Log.d(TAG, "getNfcData: ${record.tnf}")

                val type = String(record.type)
                when (type) {
                    "T" -> {
                        val payload = record.payload
                        Column(modifier = Modifier.padding(4.dp)) {
                            Text(text = "payload : $payload")
                        }
                    }
                    "U" -> {
                        val uri = record.toUri()
                        context.startActivity(Intent().apply {
                            setAction(Intent.ACTION_SENDTO)
                            data = uri
                            Column(modifier = Modifier.padding(4.dp)) {
                                Text(text = "uri : $uri")
                            }
                        })
                    }
                    "Sp" -> {
                        Log.d(TAG, "getNfcData: ${String(record.type)}")
                        Column(modifier = Modifier.padding(4.dp)) {
                            Text(text = "test")
                        }
                    }
                }
            }
            false
        }
    }
} // End of getNfcData

private fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
} // End of Context.findActivity
