package com.app.jetpacknfctutorial

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.jetpacknfctutorial.graphs.SetUpNavGraph
import com.app.jetpacknfctutorial.ui.theme.JetpackNFCTutorialTheme
import java.util.*

private const val TAG = "MainActivity_싸피"

class MainActivity : ComponentActivity() {
    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var pendingIntent: PendingIntent
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


        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC Tag", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

//        pendingIntent = PendingIntent.getActivity(
//            this,
//            0,
//            Intent(this, this.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
//            0
//        )


    } // End of onCreate

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")


//        if (nfcAdapter != null) {
//            if (!nfcAdapter.isEnabled) {
//
//                showWirelessSettings()
//                nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null)
//            }
//        }

    } // End of onResume

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")

        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this)
        }
    } // End of onPause


    private fun showWirelessSettings() {
        Toast.makeText(this, "You need to enable NFC", Toast.LENGTH_SHORT).show()
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        startActivity(intent)
    } // End of showWirelessSettings


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: ")

        setIntent(intent)
        resolveIntent(intent!!)
    } // End of onNewIntent

    private fun resolveIntent(intent: Intent) {
        val action = intent.action

        if (NfcAdapter.ACTION_TAG_DISCOVERED == action ||
            NfcAdapter.ACTION_TECH_DISCOVERED == action ||
            NfcAdapter.ACTION_NDEF_DISCOVERED == action
        ) {
            val rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            var msgs: Array<NdefMessage> = arrayOf()



            Log.d(TAG, "resolveIntent: ${rawMsgs}")
        }
    } // End of resolveIntent

    fun showMsg(mMessage: NdefMessage) {
        val recs = mMessage.records
        Log.d(TAG, "showMsg recs : $recs ")

        for (i in recs.indices) {
            val record = recs[i]
            if (Arrays.equals(record.type, NdefRecord.RTD_URI)) {
                val u: Uri = record.toUri()
                val j = Intent(Intent.ACTION_VIEW)
                j.data = u
                startActivity(j)
                finish()
            }
        }
    } // End of showMsg

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
                    nfcViewModel.setUpdateNfcPayLoad(strPload.substring(3))

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
} // End of MainActivity class
