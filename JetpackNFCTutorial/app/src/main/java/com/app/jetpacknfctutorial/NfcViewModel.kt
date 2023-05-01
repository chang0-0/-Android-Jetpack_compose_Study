package com.app.jetpacknfctutorial

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

private const val TAG = "NfcViewModel_싸피"

class NfcViewModel : ViewModel() {
    private val _nfcData = MutableLiveData<String>()
    val nfcData: LiveData<String>
        get() = _nfcData

    fun nfcDataSetData(newValue: String) {
        Log.d(TAG, "nfcDataSetData: $newValue")
        Log.d(TAG, "nfcDataSetData: ${_nfcData.value}")
        _nfcData.value = newValue
    } // End of nfcDataSetData

    private val _nfcPayLoad = MutableStateFlow("")
    val nfcPayLoad: StateFlow<String> = _nfcPayLoad.asStateFlow()

    fun setNfcPayLoad(payload: String) {
        _nfcPayLoad.value = payload
        Log.d(TAG, "_nfcPayLoad.value : ${_nfcPayLoad.value} ")
    } // End of setNfcPayLoad


    private val _updateNfcFlow = mutableStateOf("")
    val updateNfcFlow: State<String> = _updateNfcFlow

    fun setUpdateNfcPayLoad(payload: String) {
        _updateNfcFlow.value = payload
    } // End of setUpdateNfcPayLoad

    val countDownFlow = flow<Int> {
        val startingValue = 10
    }

    private val _viewState = MutableStateFlow("")
    val viewState = _viewState.asStateFlow()

    fun setViewState(newValue: String) {
        _viewState.update {
            newValue
        }
    } // End of setViewState


    private val _nfcLiveData = MutableLiveData<String>()
    val nfcLiveData: LiveData<String> = _nfcLiveData

    fun setNfcData(nfcData: String) {
        _nfcData.value = nfcData
    } // End of setNfcData


} // End of NfcViewModel class
