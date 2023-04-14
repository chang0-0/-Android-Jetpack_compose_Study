package com.app.shrine

import androidx.compose.material.BackdropScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.shrine.ui.theme.ShrineTheme


@Composable
fun Backdrop() {
//    BackdropScaffold(
//        appBar = { /*TODO*/ },
//        backLayerContent = { /*TODO*/ }) {
//
//    }
} // End ofBackdrop

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