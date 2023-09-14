package com.app.composechart

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.*


@Composable
fun LineChartScreen() {
    val steps = 5
    val pointsData = listOf(
        Point(0f, 40f),
        Point(1f, 90f),
        Point(2f, 10f),
        Point(3f, 20f),
        Point(4f, 30f),
    )


    val xAxisData = AxisData.Builder().axisStepSize(100.dp).backgroundColor(Color.Transparent)
        .steps(pointsData.size - 1).labelData { idx ->
            idx.toString()
        }.labelAndAxisLinePadding(15.dp).axisLineColor(MaterialTheme.colorScheme.error)
        .axisLabelColor(MaterialTheme.colorScheme.error).build()


    val yAxisData = AxisData.Builder().steps(steps).backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp).labelData { idx ->
            val yScale = 100 / steps
            (idx * yScale).toString()
        }.axisLineColor(MaterialTheme.colorScheme.error)
        .axisLabelColor(MaterialTheme.colorScheme.error)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = MaterialTheme.colorScheme.error,
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.error
                    ),
                    SelectionHighlightPoint(
                        color = Color.Yellow
                    ),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Blue,
                                Color.Transparent
                            )
                        )
                    ),
                    SelectionHighlightPopUp()
                )
            )
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(
            color = MaterialTheme.colorScheme.outlineVariant
        )
    )

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        lineChartData = lineChartData
    )
}