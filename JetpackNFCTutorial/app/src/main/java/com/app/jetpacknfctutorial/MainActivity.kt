package com.app.jetpacknfctutorial

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.jetpacknfctutorial.graphs.SetUpNavGraph
import com.app.jetpacknfctutorial.ui.theme.JetpackNFCTutorialTheme

private const val TAG = "MainActivity_싸피"

class MainActivity : ComponentActivity() {
    private lateinit var nAdapter: NfcAdapter
    private lateinit var pIntent: PendingIntent
    private lateinit var filters: Array<IntentFilter>
    private lateinit var navController: NavHostController
    private val nfcViewModel by viewModels<NfcViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackNFCTutorialTheme {
                navController = rememberNavController()
                SetUpNavGraph(navController = navController)
            }
        }

        nAdapter = NfcAdapter.getDefaultAdapter(this)
        val intent = Intent(this, MainActivity::class.java)
        pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        val filter = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        filters = arrayOf(filter)

        processNFC(getIntent())
    } // End of onCreate

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
        nAdapter.enableForegroundDispatch(this, pIntent, filters, null)
    } // End of onResume

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
        nAdapter.disableForegroundDispatch(this)
    } // End of onPause

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //getNfCData(getIntent())
        processNFC(intent!!)
    } // End of onNewIntent

    private fun processNFC(intent: Intent) {
        val message = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
        if (message != null) {
            message.forEach {
                Log.d(TAG, "processNFC: $it")
                val ndef = it as NdefMessage

                for (rec in ndef.records) {
                    Log.d(TAG, "TNF  : ${rec.tnf}")
                    Log.d(TAG, "ID   : ${String(rec.id)}")
                    Log.d(TAG, "TYPE : ${String(rec.type)}")
                    Log.d(TAG, "PLoad: ${String(rec.payload)}")

                    val strPload = String(rec.payload)
                    Log.d(TAG, "processNFC: ${strPload.substring(3)}")
                    nfcViewModel.setNfcPayLoad(strPload.substring(3))

                    val type = String(rec.type)
                    when (type) {
                        "T" -> {
                            val payload = rec.payload
                            Log.d(TAG, "processNFC payload: ${String(payload)}")
                        }
                        "U" -> {
                            val uri = rec.toUri()
                            startActivity(Intent().apply {
                                setAction(Intent.ACTION_SENDTO)
                                data = uri
                                Log.d(TAG, "processNFC: $uri")
                            })
                        }
                        "Sp" -> {
                            Log.d(TAG, "processNFC: ${String(rec.type)}")
                        }
                    }
                }
            }
        }
    } // End of processNFC

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
