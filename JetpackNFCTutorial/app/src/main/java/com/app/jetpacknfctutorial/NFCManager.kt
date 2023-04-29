package com.app.jetpacknfctutorial

import android.app.Activity
import android.content.Context
import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log


private const val TAG = "NFCManager_싸피"

object NFCManager {

    fun enableReaderMode(
        context: Context,
        activity: Activity,
        callback: NfcAdapter.ReaderCallback,
        flags: Int,
        extras: Bundle
    ) {

        try {
            NfcAdapter.getDefaultAdapter(context)
                .enableReaderMode(activity, callback, flags, extras)
        } catch (e: Exception) {
            Log.e(TAG, "enableReaderMode: ${e.message}")
        }

        fun disableReaderMode(context: Context, activity: Activity) {
            try {
                NfcAdapter.getDefaultAdapter(context).disableReaderMode(activity)
            } catch (e: Exception) {
                Log.e(TAG, "disableReaderMode: ${e.message}")
            }
        } // End of disableReaderMode

        fun isSupported(context: Context): Boolean {
            val nfcAdapter = NfcAdapter.getDefaultAdapter(context)
            return if (nfcAdapter == null) false
            else true
        } // End of isSupport

        fun isNotSupported(context: Context): Boolean {
            val nfcAdapter = NfcAdapter.getDefaultAdapter(context)
            return if (nfcAdapter == null) false
            else nfcAdapter.isEnabled
        } // End of isNotSupported

        fun isEnabled(context: Context): Boolean {
            val nfcAdapter = NfcAdapter.getDefaultAdapter(context)
            return if (nfcAdapter == null) false
            else true
        } // End of isEnabled

        fun isNotEnabled(context: Context): Boolean {
            val nfcAdapter = NfcAdapter.getDefaultAdapter(context)
            return if (nfcAdapter == null) true
            else nfcAdapter.isEnabled().not()
        } // End of isNotEnabled

        fun isSupportedAndEnabled(context: Context): Boolean {
            return isSupported(context) && isEnabled(context)
        } // End of isSupportedAndEnabled
    } // End of enableReaderMode
} // End of NFCManager
