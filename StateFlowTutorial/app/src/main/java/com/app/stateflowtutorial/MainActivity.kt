package com.app.stateflowtutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.stateflowtutorial.ui.theme.StateFlowTutorialTheme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateFlowTutorialTheme {
                val viewModel = viewModel<MainViewModel>()
                //val composeColor = viewModel.composeColor
                val flowColor by viewModel.color.collectAsState()

                val textVisible by remember {
                    mutableStateOf(true)
                }

                // Box Click시 viewModel.generateNewColor() 메소드 실행
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(flowColor))

                ) {
                    Column {
                        Text(text = "HelloWorld",
                            modifier = Modifier.clickable {

                            }
                        )
                    }
                    Spacer(Modifier.padding(40.dp))
                    Button(
                        onClick = {

                        }
                    ) {
                        Text(text = "Button")
                    }
                }
            }
            AnimateVisibility()
        }

        subscribeToObservables()
    } // End of onCreate

    private fun subscribeToObservables() {
        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collectLatest {

            }
        }
    } // End of subscribeToObservables


    @Composable
    private fun AnimateVisibility() {
        var visible by remember {
            mutableStateOf(true)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AnimatedVisibility(visible = visible) {
                Text(text = "Hello, World")
            }
            Button(onClick = { visible = !visible }) {
                Text("Click Me")
            }
        }
    } // End of AnimateVisibility
} // End of MainActivity
