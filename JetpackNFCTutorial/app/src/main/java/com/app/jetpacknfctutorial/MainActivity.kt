package com.app.jetpacknfctutorial

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.nfc.*
import android.os.Bundle
import android.util.Log
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

        initNfcAdapter()

        nAdapter = NfcAdapter.getDefaultAdapter(this)
        val intent = Intent(this, javaClass)
        pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        val filter = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        filters = arrayOf(filter)
        processNFC(getIntent())
    } // End of onCreate

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
        //nAdapter.enableForegroundDispatch(this, pIntent, filters, null)

//        NfcAdapter.getDefaultAdapter(this)?.let { nfcAdapter ->
//
//            val launchIntent = Intent(this, this.javaClass)
//            launchIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//
//            val pendingIntent = PendingIntent.getActivity(
//                this, 0, launchIntent, PendingIntent.FLAG_CANCEL_CURRENT
//            )
//
//
//            val filters = arrayOf(IntentFilter(ACTION_TECH_DISCOVERED))
//            val techTypes = arrayOf(arrayOf(IsoDep::class.java.name))
//
//            nfcAdapter.enableForegroundDispatch(
//                this, pendingIntent, filters, techTypes
//            )
//        }


        enableNfcForegroundDispatch()

    } // End of onResume

    private fun enableNfcForegroundDispatch() {
        try {
            val intent = Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            val nfcPendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            adapter?.enableForegroundDispatch(this, nfcPendingIntent, null, null)
        } catch (e: Exception) {
            Log.d(TAG, "enableNfcForegroundDispatch: Error")
        }
    } // End of enableNfcForegroundDispatch

    override fun onPause() {
        Log.d(TAG, "onPause: ")
//        if (this.isFinishing) {
//            nAdapter.disableForegroundDispatch(this)
//        }

        if (nAdapter != null) {
            nAdapter.disableForegroundDispatch(this)
        }

        super.onPause()
    } // End of onPause

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (intent!!.action == NfcAdapter.ACTION_TAG_DISCOVERED) {
            val messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            Log.d(TAG, "onNewIntent: $messages")

            for (i in messages!!.indices) {
                showMsg(messages[i] as NdefMessage)
            }
        }

        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessage ->

                val messages: List<NdefMessage> = rawMessage.map {
                    it as NdefMessage
                }
            }
        }

        val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
        Log.d(TAG, "tag : $tag ")

        processNFC(intent!!)
    } // End of onNewIntent

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

    private var adapter: NfcAdapter? = null

    private fun initNfcAdapter() {
        val nfcManager = getSystemService(Context.NFC_SERVICE) as NfcManager
        adapter = nfcManager.defaultAdapter
    } // End of initNfcAdapter

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
