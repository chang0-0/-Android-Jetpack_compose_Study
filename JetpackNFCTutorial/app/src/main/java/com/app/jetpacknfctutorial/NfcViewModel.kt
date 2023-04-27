package com.app.jetpacknfctutorial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NfcViewModel : ViewModel() {
    private val _nfcData = MutableLiveData<String>()
    val nfcData: LiveData<String> = _nfcData

    private fun nfcDataSetData(newValue: String) {
        _nfcData.value = newValue
    } // End of nfcDataSetData

    private val _nfcPayLoad = MutableStateFlow<String>("")
    val nfcPayLoad: StateFlow<String>
        get() = _nfcPayLoad

    fun setNfcPayLoad(payload: String) {
        _nfcPayLoad.value = payload
    } // End of setNfcPayLoad
} // End of NfcViewModel class
