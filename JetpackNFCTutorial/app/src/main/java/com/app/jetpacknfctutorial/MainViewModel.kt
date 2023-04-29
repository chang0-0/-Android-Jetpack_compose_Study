package com.app.jetpacknfctutorial

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

private const val TAG = "MainViewModel_싸피"

class MainViewModel : AndroidViewModel {
    constructor(application: Application) : super(application) {
        Log.d(TAG, ": ")

        liveNFC = MutableStateFlow(null)
        liveToast = MutableSharedFlow()
        liveTag = MutableStateFlow(null)
    }

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
        private const val prefix = "android.nfc.tech"
    }

    private val liveNFC: MutableStateFlow<NFCStatus?>
    private val liveToast: MutableSharedFlow<String?>
    private val liveTag: MutableStateFlow<String?>



} // End of MainViewModel