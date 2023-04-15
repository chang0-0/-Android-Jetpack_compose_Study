package com.app.shrine

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.shrine.ui.theme.ShrineTheme


@ExperimentalMaterialApi
@Composable
fun Backdrop() {
    BackdropScaffold(
        appBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_menu_cut_24px),
                            contentDescription = "Menu icon"
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            "Shrine".uppercase(),
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search action"
                    )
                },
                elevation = 0.dp
            )
        },
        frontLayerContent = {
            Column(Modifier.padding(16.dp)) {
                Text("This is the content for the front")
            }
        },
        frontLayerShape = MaterialTheme.shapes.large,
        backLayerContent = {
            Column(Modifier.padding(32.dp)) {
                Text("This is the content for the back")
            }
        }
    )
} // End ofBackdrop

@ExperimentalMaterialApi
@Preview
@Composable
fun BackdropPreview() {
    ShrineTheme {
        Backdrop()
    }


    /*
        // 변경되는 값의 상태를 기억해야 한다는 의미를 가짐.
    var total by remember {
        mutableStateOf(0)
    }

    Column {
        Text("The total is $total")
        Button(onClick = {
            total++
        }) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "face"
            )
            Spacer(
                Modifier.width(12.dp)
            )
            Text("Increment")
        }
    }
     */


}