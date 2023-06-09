package com.app.shrine

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
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
    cartSize: Int,
    onCollapse: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onCollapse() }, Modifier.padding(4.dp)) {
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
            Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.secondary
        ) {
            CartHeader(cartSize = 15, onCollapse = {})
        }
    }
} // End of CartHeaderPreview

@Composable
private fun CartItem(
    modifier: Modifier = Modifier,
    item: ItemData,
    onRemoveAction: () -> Unit = {}
) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onRemoveAction() }, Modifier.padding(horizontal = 4.dp)) {
            Icon(
                imageVector = Icons.Default.RemoveCircleOutline,
                contentDescription = "Remove item icon"
            )
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
    }

} // End of CartItem

@Preview(name = "Cart item")
@Composable
fun CartItemPreview() {
    ShrineTheme {
        Surface(color = MaterialTheme.colors.secondary) {
            CartItem(item = SampleItems[0])
        }
    }
} // End of CartItemPreview


data class ExpandedCartItem @JvmOverloads constructor(
    val idx: Int,
    val visible: MutableTransitionState<Boolean> = MutableTransitionState(true),
    val data: ItemData
) // End of ExpandedCartItem class

@Composable
fun ExpandedCart(
    items: List<ExpandedCartItem>,
    onRemoveItem: (ExpandedCartItem) -> Unit = {},
    onCollapse: () -> Unit = {}
) {
    Surface(
        color = MaterialTheme.colors.secondary
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 64.dp)
        ) {
            item {
                CartHeader(
                    cartSize = items.size,
                    onCollapse = onCollapse
                )
            }
            itemsIndexed(
                items = items,
                key = { idx, item -> "$idx-${item.data.id}" }
            ) { idx, it ->
                AnimatedVisibility(
                    visibleState = it.visible,
                    exit = fadeOut() + slideOut(targetOffset = {
                        IntOffset(
                            x = -it.width / 2,
                            y = 0
                        )
                    })
                ) {
                    CartItem(
                        item = it.data,
                        onRemoveAction = { onRemoveItem(it) }
                    )
                }
            }
        }
    }
} // End of ExpandedCart

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
    items: List<ItemData> = SampleItems.subList(fromIndex = 0, toIndex = 3),
    onTap: () -> Unit = {}
) {
    Row(
        Modifier
            .padding(start = 24.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
            .clickable { onTap() },
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            Modifier.size(40.dp), contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Shopping cart icon"
            )
        }
        items.forEach { item ->
            CollapsedCartItem(data = item)
        }
    }
} // End of CollapsedCart

@Composable
private fun CollapsedCartItem(
    data: ItemData
) {
    Image(
        painter = painterResource(id = data.photoResId),
        contentDescription = data.title,
        alignment = Alignment.TopCenter,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(10.dp))
    )
} // End of CollapsedCartItem

@Preview
@ExperimentalAnimationApi
@Composable
fun CartExpandingBottomSheetPreview() {
    ShrineTheme {
        BoxWithConstraints(
            Modifier.fillMaxSize()
        ) {
            var sheetState by remember {
                mutableStateOf(CartBottomSheetState.Collapsed)
            }

            Button(
                onClick = {
                    if (sheetState == CartBottomSheetState.Collapsed) {
                        sheetState = CartBottomSheetState.Hidden
                    } else {
                        sheetState = CartBottomSheetState.Collapsed
                    }
                }
            ) {
                Text("Toggle BottomSheet")
            }

            CartBottomSheet(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                sheetState = sheetState,
                maxHeight = maxHeight,
                maxWidth = maxWidth
            ) {
                sheetState = it
            }
        }
    }
} // End of CollapsedCartPreview


@ExperimentalAnimationApi
@Composable
fun CartBottomSheet(
    modifier: Modifier = Modifier,
    items: List<ItemData> = SampleItems.take(14),
    maxHeight: Dp,
    maxWidth: Dp,
    sheetState: CartBottomSheetState = CartBottomSheetState.Collapsed,
    onRemoveItemCart: (Int) -> Unit = {},
    onRemoveItemFromCart: (Int) -> Unit = {},
    onSheetStateChange: (CartBottomSheetState) -> Unit = {}
) {
    val expandedCartItems by remember(items) {
        derivedStateOf {
            items.mapIndexed { idx, it ->
                ExpandedCartItem(idx = idx, data = it)
            }
        }
    }

    LaunchedEffect(expandedCartItems) {
        snapshotFlow {
            expandedCartItems.firstOrNull {
                it.visible.isIdle && it.visible.targetState
            }
        }.collect {
            if (it != null) {
                onRemoveItemFromCart(it.idx)
            }
        }
    }

    val cartTransition = updateTransition(
        targetState = sheetState,
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
                else -> {
                    tween(durationMillis = 450)
                }
            }
        }
    ) {
        when (it) {
            CartBottomSheetState.Expanded -> 0.dp
            CartBottomSheetState.Hidden -> maxWidth
            else -> {
                val size = min(3, items.size)
                var width = 24 + 40 * (size + 1) + 16 * size + 16
                if (items.size > 3) {
                    width += 32 + 16
                }
                maxWidth - width.dp
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


    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(cartHeight)
            .offset { IntOffset(cartXOffset.roundToPx(), 0) },
        shape = CutCornerShape(topStart = cornerSize),
        color = MaterialTheme.colors.secondary,
        elevation = 8.dp
    ) {
        Box {
            cartTransition.AnimatedContent(
                transitionSpec = {
                    when {
                        CartBottomSheetState.Expanded isTransitioningTo CartBottomSheetState.Collapsed ->
                            fadeIn(
                                animationSpec = tween(
                                    durationMillis = 117,
                                    delayMillis = 117,
                                    easing = LinearEasing
                                )
                            ) with
                                    fadeOut(
                                        animationSpec = tween(
                                            durationMillis = 117,
                                            easing = LinearEasing
                                        )
                                    )
                        CartBottomSheetState.Collapsed isTransitioningTo CartBottomSheetState.Expanded ->
                            fadeIn(
                                animationSpec = tween(
                                    durationMillis = 150,
                                    delayMillis = 150,
                                    easing = LinearEasing
                                )
                            ) with
                                    fadeOut(
                                        animationSpec = tween(
                                            durationMillis = 150,
                                            easing = LinearEasing
                                        )
                                    )
                        else -> EnterTransition.None with ExitTransition.None
                    }.using(SizeTransform(clip = false))
                },
            ) { targetState ->
                if (targetState == CartBottomSheetState.Expanded) {
                    ExpandedCart(
                        items = expandedCartItems,
                        onRemoveItem = {
                            it.visible.targetState = false
                        },
                        onCollapse = {
                            onSheetStateChange(CartBottomSheetState.Collapsed)
                        }
                    )
                } else {
                    CollapsedCart(
                        items = items,
                        onTap = {
                            onSheetStateChange(CartBottomSheetState.Expanded)
                        }
                    )
                }
            }
        }
    }
} // End of CartExpandingBottomSheet
