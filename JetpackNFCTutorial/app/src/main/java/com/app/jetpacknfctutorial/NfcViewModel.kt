package com.app.jetpacknfctutorial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val TAG = "NfcViewModel_싸피"

class NfcViewModel : ViewModel() {
    private val _nfcData = MutableLiveData<String>()
    val nfcData: LiveData<String>
        get() = _nfcData

    fun setNfcData(newNfcData: String) {
        _nfcData.value = newNfcData
        Log.d(TAG, "setNfcData: $newNfcData")
    } // End of setNfcData

    private val _nfcState = MutableStateFlow<String>("")
    val nfcState: StateFlow<String> = _nfcState

    fun setNfcState(newNFCState: String) {
        _nfcState.value = newNFCState
    } // End of setNfcState
} // End of NfcViewModel class
