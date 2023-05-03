package com.app.composestatetutorial2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.composestatetutorial2.ui.theme.ComposeStateTutorial2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStateTutorial2Theme {
                HelloContent()
            }
        }
    }
}

class HelloViewModel : ViewModel() {
    private val _name: MutableLiveData<String> = MutableLiveData("")
    val name: LiveData<String> = _name

    fun onNameChange(newName: String) {
        _name.value = newName
    } // End of onNameChange



} // End of HelloViewModel

@Composable
fun HelloScreen(helloViewModel: HelloViewModel = viewModel()) {
    val name by helloViewModel.name.observeAsState("")
    HelloContent(name = name, onNameChange = { helloViewModel.onNameChange(it) })
} // End of HelloScreen

@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        if (text.isNotEmpty()) {
            Text(text = "Hello! $text")
        }
        OutlinedTextField(value = text, onValueChange = { text = it }, label = { Text("Name") })
    }
} // End of HelloContent
