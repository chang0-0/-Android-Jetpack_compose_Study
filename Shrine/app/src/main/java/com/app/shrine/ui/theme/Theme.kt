package com.app.shrine.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ShrineTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ShrineLightColorPalette,
        typography = ShrineTypography,
        shapes = ShrineShape,
        content = content
    )
} // End of ShrineTheme

@Preview(showBackground = true)
@Composable
fun ShrineComponentTest() {
    Column(
        Modifier.padding(32.dp)
    ) {
        ShrineTheme {
            Column {
                ShrineButton()
            }
        }
        Spacer(Modifier.height(32.dp))
        MaterialTheme {
            Column {
                Button(onClick = { }) {
                    Text(text = "This is a Material baseline button")
                }
            }
        }
    }
} // End of ShrineComponentTest

@Composable
private fun ShrineButton() {
    Button(onClick = { }) {
        Icon(
            imageVector = Icons.Default.Face,
            contentDescription = "Face"
        )
        Spacer(Modifier.width(48.dp))
        Text(text = "This is a Shrine button".uppercase())
    }
}

//@Preview(name = "Typography test", widthDp = 720, showBackground = true)
//@Composable
//fun TypographyThemeTest() {
//    ShrineTheme {
//        Column {
//            Text(
//                "H1 / Rubik Light",
//                style = MaterialTheme.typography.h1
//            )
//            Text(
//                "H2 / Rubik Light",
//                style = MaterialTheme.typography.h2
//            )
//            Text(
//                "H3 / Rubik Regular",
//                style = MaterialTheme.typography.h3
//            )
//            Text(
//                "Body1 / Rubik Regular",
//                style = MaterialTheme.typography.body1
//            )
//            Text(
//                "Button / Rubik Medium".uppercase(),
//                style = MaterialTheme.typography.button
//            )
//            Text(
//                "Caption / Rubik Regular",
//                style = MaterialTheme.typography.caption
//            )
//        }
//    }
//} // End of TypographyThemeTest