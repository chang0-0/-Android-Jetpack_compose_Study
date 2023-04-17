package com.app.shrine

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.shrine.ui.theme.ShrineTheme

//@Preview(name = "Cart item")
//@Composable
//fun CartItemPreview() {
//    ShrineTheme {
//        Surface(color = MaterialTheme.colors.secondary) {
//            CartItem(SampleItemsData[0])
//        }
//    }
//} // End of CartItemPreview


@Composable
private fun CartHeader(
    cartSize: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}, Modifier.padding(4.dp)) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Collapse cart icon"
            )
        }
        Text(
            "Cart".uppercase(),
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(Modifier.width(12.dp))
        Text("$cartSize items".uppercase())
    }
} // End of CartHeader

@Preview(name = "Cart header")
@Composable
fun CartHeaderPreview() {
    ShrineTheme {
        Surface(
            Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.secondary
        ) {
            CartHeader(cartSize = 15)
        }
    }
} // End of CartHeaderPreview


@Composable
fun Cart() {
    Surface(color = MaterialTheme.colors.surface) {
        // width 전체를 차지하는 버튼
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val cartSize = 6

            // 수직 중앙정렬
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Down arrow"
                    )
                }
                Spacer(Modifier.width(4.dp))
                Text("Cart".uppercase())
                Spacer(Modifier.width(12.dp))
                Text("${SampleItemsData.size} items".uppercase())
            }

            SampleItemsData.forEach { item ->
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {},
                        Modifier.padding(horizontal = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.RemoveCircleOutline,
                            contentDescription = "Remove item icon"
                        )
                    }
                    Column(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        Divider(
                            color = MaterialTheme.colors.onSecondary.copy(alpha = 0.3f)
                        )
                        Row(
                            Modifier
                                .padding(vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(
                                Modifier
                                    .height(120.dp)
                                    .width(120.dp)
                            ) {
                                Image(
                                    contentScale = ContentScale.Crop,
                                    painter = painterResource(id = item.photoResId),
                                    contentDescription = "Item image",
                                )
                            }
                            Column(Modifier.padding(end = 16.dp)) {
                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Vendor ${item.vendor}".uppercase(),
                                        style = MaterialTheme.typography.body2
                                    )
                                    Text(
                                        text = "Price ${item.price}",
                                        style = MaterialTheme.typography.body2
                                    )
                                }
                                Text(
                                    text = "title of item ${item.title}",
                                    style = MaterialTheme.typography.subtitle2
                                )
                            }
                        }
                    }
                }
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text("Proceed to checkout".uppercase())
            }
        }
    }
} // End of Cart

@Preview
@Composable
fun CartPreview() {
    ShrineTheme {
        Cart()
    }
} // End of CartPreview
