package com.example.navigationtutorial

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.navigationtutorial.ui.theme.NavigationTutorialTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ImageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTutorialTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    viewModel.uri?.let {
                        AsyncImage(model = viewModel.uri, contentDescription = null)
                    }
                    Button(onClick = {
//                        Intent(applicationContext, SecondActivity::class.java).also {
//                            startActivity(it)
//                        }
//                        Intent(Intent.ACTION_MAIN).also {
//                            it.`package` = "com.google.android.youtube"
//                            try {
//                                startActivity(it)
//                            } catch (e: ActivityNotFoundException) {
//                                e.printStackTrace()
//                            }
//                        }

                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("test@test.com"))
                            putExtra(Intent.EXTRA_SUBJECT, "This is my subject")
                            putExtra(Intent.EXTRA_TEXT, "This is the content of my email")
                        }

                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent)
                        }
                    }, Modifier.size(120.dp)) { Text(text = "Button", fontSize = 24.sp) }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        // API 레벨이 33이상만 가능
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        viewModel.updateUri(uri)
    } // End of onNewIntent
} // End of MainActivity class


@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()

    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return viewModel(parentEntry)
} // End of NavBackStackEntry.sharedViewModel

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
} // End of Greeting

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationTutorialTheme {
        Greeting("Android")
    }
} // End of DefaultPreview