package com.app.shrine

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ShrineApp() {
    var sheetState by rememberSaveable { mutableStateOf(CartBottomSheetState.Collapsed) }
    val cartItems = remember { mutableStateListOf(*SampleItems.take(2).toTypedArray()) }
    var firstAddCartItem by remember { mutableStateOf<FirstAddCartItemData?>(null) }

    BoxWithConstraints(
        Modifier.fillMaxSize()
    ) {
        Backdrop(
            showScrim = sheetState == CartBottomSheetState.Expanded,
            onAddCartItem = {
                if (cartItems.isEmpty()) firstAddCartItem = it
                cartItems.add(it.data)
            },
            onBackdropReveal = { revealed ->
                sheetState = if (revealed) CartBottomSheetState.Hidden else CartBottomSheetState.Collapsed
            }
        )
        CartBottomSheet(
            modifier = Modifier.align(Alignment.BottomEnd),
            items = cartItems,
            sheetState = sheetState,
            maxHeight = maxHeight,
            maxWidth = maxWidth,
            onRemoveItemFromCart = {
                cartItems.removeAt(it)
                if (cartItems.isEmpty()) {
                    firstAddCartItem = null
                }
            },
            onSheetStateChange = {
                sheetState = it
            }
        )
        if (firstAddCartItem != null) {
            FirstAddCartItem(data = firstAddCartItem!!) {
                firstAddCartItem = null
            }
        }
    }
} // End of ShrineApp

enum class CartBottomSheetState {
    Collapsed,
    Expanded,
    Hidden
} // End of CartBottomSheetState

