package com.app.jetpacknfctutorial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "NfcViewModel_싸피"

class NfcViewModel : ViewModel() {
    private val _nfcData = MutableLiveData<String>()
    val nfcData: LiveData<String>
        get() = _nfcData

    fun setNfcData(newNfcData: String) {
        _nfcData.value = newNfcData
        Log.d(TAG, "setNfcData: $newNfcData")
    } // End of setNfcData

} // End of NfcViewModel class
