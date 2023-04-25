package com.app.jetpacknfctutorial

import android.app.PendingIntent
import android.content.ContentValues.*
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.jetpacknfctutorial.graphs.SetUpNavGraph
import com.app.jetpacknfctutorial.ui.theme.JetpackNFCTutorialTheme

class MainActivity : ComponentActivity() {
    private lateinit var nAdapter: NfcAdapter
    private lateinit var pIntent: PendingIntent
    private lateinit var filters: Array<IntentFilter>

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val action = intent.action
//
//        nAdapter = NfcAdapter.getDefaultAdapter(this)
//        val i = Intent(this, MainActivity::class.java)
//
//        i.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
//        pIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_MUTABLE)
//
//        val ndef_filter = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
//        ndef_filter.addDataType("text/plain")
//
//        val ndef_filter2 = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
//        ndef_filter2.addDataScheme("https")
//
//
//        filters = arrayOf(ndef_filter)

        setContent {
            JetpackNFCTutorialTheme {
                navController = rememberNavController()
                SetUpNavGraph(navController = navController)
            }
        }
    } // End of onCreate

//    override fun onResume() {
//        super.onResume()
//        nAdapter.enableForegroundDispatch(this, pIntent, filters, null)
//    } // End of onResume
//
//    override fun onPause() {
//        super.onPause()
//        nAdapter.disableForegroundDispatch(this)
//    } // End of onPause

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        Toast.makeText(this, "onNewIntent", Toast.LENGTH_SHORT).show()
        val action = intent!!.action
        Log.d(TAG, "onNewIntent: $action")

        parseData(intent)
    } // End of onNewIntent

    private fun parseData(intent: Intent) {
        val data = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)

        data!!.takeWhile {
            val message = it as NdefMessage
            val record = message.records
            Log.d(TAG, "parseData: ${String(record[0].payload)} ")

            false
        }
    } // End of parseData
} // End of MainActivity class
