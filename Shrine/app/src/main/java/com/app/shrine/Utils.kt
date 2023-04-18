package com.app.shrine

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize

@Composable
fun Size.toDpSize(): DpSize {
    with(LocalDensity.current) {
        return DpSize(
            this@toDpSize.width.toDp(), this@toDpSize.height.toDp()
        )
    }
} // End of toDpSize