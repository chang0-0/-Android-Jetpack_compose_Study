package com.app.jetpacknfctutorial

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

private lateinit var nAdapter: NfcAdapter
private lateinit var pendingIntent: PendingIntent

@Composable
fun MainTextBody(
    navController: NavController,
) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //nfcRead(LocalContext.current)
        Text(text = "test", fontSize = MaterialTheme.typography.h3.fontSize, color = Color.Black)
    }
    Spacer(modifier = Modifier.padding(10.dp))
    Button(onClick = {
        navController.navigate(route = HomeScreen.Second.route)

        Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
    }) {
        Text(text = "Change Screen Button")
    }
} // End of MainTextScreen

@RequiresApi(Build.VERSION_CODES.S)
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