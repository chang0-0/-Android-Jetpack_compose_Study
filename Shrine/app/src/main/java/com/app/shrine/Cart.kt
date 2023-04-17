package com.app.shrine

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.shrine.ui.theme.ShrineTheme
import kotlin.math.min

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
            "Cart".uppercase(), style = MaterialTheme.typography.subtitle1
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
            Modifier.fillMaxWidth(), color = MaterialTheme.colors.secondary
        ) {
            CartHeader(cartSize = 15)
        }
    }
} // End of CartHeaderPreview

@Composable
private fun CartItem(
    item: ItemData
) {
    Row(
        Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}, Modifier.padding(horizontal = 4.dp)) {
            Icon(
                imageVector = Icons.Default.RemoveCircleOutline,
                contentDescription = "Remove item icon"
            )
        }
    }
    Column(
        Modifier.fillMaxWidth()
    ) {
        Divider(color = MaterialTheme.colors.onSecondary.copy(alpha = 0.3f))
        Row(
            Modifier.padding(vertical = 20.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.photoResId),
                contentDescription = "Image for : ${item.title}",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size(80.dp)
            )
            Spacer(Modifier.width(20.dp))
            Column(
                Modifier.padding(end = 16.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${item.vendor}", style = MaterialTheme.typography.body2
                    )
                    Text(
                        text = "${item.price}", style = MaterialTheme.typography.body2
                    )
                }
                Text(
                    text = item.title, style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }

} // End of CartItem

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
fun ExpandedCart(
    items: List<ItemData> = SampleItemsData
) {
    Surface(color = MaterialTheme.colors.surface) {
        // width 전체를 차지하는 버튼
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            CartHeader(cartSize = items.size)


            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEach { item ->
                    CartItem(item)
                }
            }

            Button(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(), onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.AddShoppingCart,
                    contentDescription = "Add to cart icon"
                )
                Spacer(Modifier.width(16.dp))
                Text("Add to cart".uppercase())
            }

        }
    }
} // End of Cart

//@Preview(name = "Full cart", device = Devices.PIXEL_XL)
//@Composable
//fun CartPreview() {
//    ShrineTheme {
//        ExpandedCart()
//    }
//} // End of CartPreview

@Composable
fun CollapsedCart(
    // toIndex로 갯수를 제한
    items: List<ItemData> = SampleItemsData.subList(fromIndex = 0, toIndex = 3)
) {
    Row(
        Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Box(
            Modifier.size(40.dp), contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart, contentDescription = "Shopping cart icon"
            )
        }
        items.forEach {
            CollapsedCartItem(
                it.photoResId, it.title
            )
        }
    }
} // End of CollapsedCart

@Composable
private fun CollapsedCartItem(
    photoId: Int, description: String
) {
    Image(
        painter = painterResource(id = photoId),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(40.dp)
            .clip(
                RoundedCornerShape(10.dp)
            )
    )
} // End of CollapsedCartItem


@Preview
@ExperimentalAnimationApi
@Composable
fun CollapsedCartPreview() {
    ShrineTheme {
        Surface(
            Modifier.fillMaxSize()
        ) {
            BoxWithConstraints(
                Modifier.fillMaxSize()
            ) {
                var expanded by remember { mutableStateOf(false) }

                CartExpandingBottomSheet(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clickable { expanded = !expanded },
                    expanded = expanded,
                    maxHeight = maxHeight,
                    maxWidth = maxWidth
                )
            }
        }
    }
} // End of CollapsedCartPreview


enum class CartBottomSheetState {
    Collapsed,
    Expanded,
    Hidden
} // End of CartBottomSheetState

@ExperimentalAnimationApi
@Composable
fun CartExpandingBottomSheet(
    modifier: Modifier = Modifier,
    items: List<ItemData> = SampleItemsData,
    expanded: Boolean = false,
    maxHeight: Dp,
    maxWidth: Dp,
) {
    val cartTransition = updateTransition(
        targetState = when {
            expanded -> CartBottomSheetState.Expanded
            else -> CartBottomSheetState.Collapsed
        },
        label = "cartTransition"
    )

    val cartXOffset by cartTransition.animateDp(
        label = "cartXOffset",
        transitionSpec = {
            when {
                CartBottomSheetState.Expanded isTransitioningTo CartBottomSheetState.Collapsed -> tween(
                    durationMillis = 433, delayMillis = 67
                )
                CartBottomSheetState.Collapsed isTransitioningTo CartBottomSheetState.Expanded -> tween(
                    durationMillis = 150
                )
                else -> tween(durationMillis = 450)
            }
        }
    ) {
        when (it) {
            CartBottomSheetState.Hidden -> maxWidth
            CartBottomSheetState.Collapsed -> 0.dp
            else -> {
                val size = min(2, items.size)
                var width = 24 + 40 * (size + 1) + 16 * size + 16
                (maxWidth.value - (width)).dp
            }
        }
    }

    val cartHeight by cartTransition.animateDp(
        label = "cartHeight",
        transitionSpec = {
            when {
                CartBottomSheetState.Expanded isTransitioningTo CartBottomSheetState.Collapsed -> {
                    tween(durationMillis = 283)
                }
                else -> {
                    tween(durationMillis = 500)
                }
            }
        }
    ) {
        if (it == CartBottomSheetState.Expanded) maxHeight else 56.dp
    }

    val cornerSize by cartTransition.animateDp(
        label = "cartCornerSize",
        transitionSpec = {
            when {
                CartBottomSheetState.Expanded isTransitioningTo CartBottomSheetState.Collapsed ->
                    tween(durationMillis = 433, delayMillis = 67)
                else -> {
                    tween(durationMillis = 150)
                }
            }
        }
    ) {
        if (it == CartBottomSheetState.Expanded) 0.dp else 24.dp
    }


    Box(
        modifier
            .shadow(
                elevation = 8.dp,
                shape = CutCornerShape(topStart = cornerSize)
            )
            .height(cartHeight)
            .background(
                color = MaterialTheme.colors.secondary,
                shape = CutCornerShape(topStart = cornerSize)
            )
    ) {
        AnimatedContent(
            targetState = expanded
        ) { targetState ->
            if (targetState) {
                ExpandedCart(items = items)
            } else {
                CollapsedCart(items = items.subList(fromIndex = 0, toIndex = 3))
            }
        }
    }
} // End of CartExpandingBottomSheet
