package com.app.composechart

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

@Composable
fun PieChartScreen() {
    val pieChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice(
                label = "돈까스",
                value = 65f,
                color = Color(0xFFDF5D5D)
            ),
            PieChartData.Slice(
                label = "제육",
                value = 35f,
                color = Color(0xFFFFC39A)
            ),
            PieChartData.Slice(
                label = "짜장면",
                value = 10f,
                color = Color(0xFFFDB6CE)
            ),
            PieChartData.Slice(
                label = "초밥",
                value = 40f,
                color = Color(0xFFE96D74)
            ),
            PieChartData.Slice(
                label = "떡볶이",
                value = 40f,
                color = Color(0xFFFF9367)
            )
        ),
        plotType = PlotType.Pie
    )

    val pieChartConfig = PieChartConfig(
        isAnimationEnable = true,
        showSliceLabels = true,
        isSumVisible = true,
        activeSliceAlpha = 0.5f,
        chartPadding = 5,
        sliceLabelTextColor = Color.Black,
        sliceLabelTextSize = 30.sp,
        animationDuration = 1400,
        sumUnit = "sumUnit"
    )

    PieChart(
        modifier = Modifier
            .width(440.dp)
            .height(440.dp),
        pieChartData = pieChartData,
        pieChartConfig = pieChartConfig
    )
} // End of PieChartScreen()