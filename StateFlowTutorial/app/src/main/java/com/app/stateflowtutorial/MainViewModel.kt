package com.app.stateflowtutorial


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class MainViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val color = savedStateHandle.getStateFlow("color", 0xFFFFFFFF)

//    var composeColor by mutableStateOf(
//        savedStateHandle.get<Long>("color") ?: 0xFFFFFFFF
//    )
//        private set

//    var composeColor by mutableStateOf(0xFFFFFFFF)
//        private set

    fun generateNewColor() {
        val color = Random.nextLong(0xFFFFFFFF)
        savedStateHandle["color"] = color
    } // End of generateNewColor


    private val _stateFlow = MutableStateFlow("Hello World")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun triggerStateFlow() {
        _stateFlow.value = "StateFlow"
    } // End of triggerStateFlow
} // End of MainViewModel class
