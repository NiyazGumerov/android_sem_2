package ru.itis.homework9.presentation.ui

import android.graphics.DashPathEffect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect.Companion.dashPathEffect
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GraphItem(
    pointsCount: Int,
    pointsValues: List<Int>,
) {

    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
            .padding(8.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val textStyle = TextStyle(
            color = Color.Black,
            fontSize = 12.sp,
        )

        val graphWidth = canvasWidth - 100
        val graphHeight = canvasHeight - 100
        val graphXStart = 100f
        val graphYStart = 0f
        val graphXEnd = graphXStart + graphWidth
        val graphYEnd = graphYStart + graphHeight

        val pointOffset = graphWidth / (pointsCount - 1)
        val maxValue = pointsValues.maxOrNull() ?: 1
        val minValue = pointsValues.minOrNull() ?: 0

        val listOffset = mutableListOf<Offset>()

        //Первая тёмная вертикальная линия
        drawLine(
            start = Offset(graphXStart, graphYStart),
            end = Offset(graphXStart, graphYEnd + 30f),
            color = Color.Gray,
            strokeWidth = 2f,
        )
        //Остальные пунктирные вертикальные линии
        for (i in 1 until pointsCount) {
            val x = graphXStart + i * pointOffset
            drawLine(
                start = Offset(x, 0f),
                end = Offset(x, graphYEnd),
                color = Color.LightGray,
                strokeWidth = 2f,
                pathEffect = dashPathEffect(floatArrayOf(5f, 5f), 0f)
            )
        }
        //Первая тёмная горизонтальная линия
        drawLine(
            start = Offset(graphXStart - 30f, graphYEnd),
            end = Offset(graphXEnd, graphYEnd),
            color = Color.Gray,
            strokeWidth = 2f,
        )
        var j = 0
        //Остальные горизонтальные линии
        while (pointOffset * j < graphYEnd) {
            //пунктирные
            drawLine(
                start = Offset(graphXStart, j * pointOffset),
                end = Offset(graphXEnd, j * pointOffset),
                color = Color.LightGray,
                strokeWidth = 2f,
                pathEffect = dashPathEffect(floatArrayOf(5f, 5f), 0f)
            )
            //тёмные
            drawLine(
                start = Offset(graphXStart - 30f, j * pointOffset),
                end = Offset(graphXStart, j * pointOffset),
                color = Color.Gray,
                strokeWidth = 2f,
            )
            //текст оси Y
            drawText(
                textMeasurer,
                (maxValue - (j * pointOffset) / graphHeight * (maxValue - minValue)).toInt()
                    .toString(),
                Offset(graphXStart - 90f, j * pointOffset - 20f),
                style = textStyle
            )
            j++
        }

        for (i in 0 until pointsCount) {
            val x = graphXStart + i * pointOffset
            val y =
                (1 - ((pointsValues[i] - minValue).toFloat() / (maxValue - minValue))) * graphHeight

            drawText(
                textMeasurer,
                (i + 1).toString(),
                Offset(x - 9f, graphYEnd + 50f),
                style = textStyle
            )


            listOffset.add(Offset(x, y))

            //тёмные вертикальные линии
            drawLine(
                start = Offset(x, graphYEnd),
                end = Offset(x, graphYEnd + 30f),
                color = Color.Gray,
                strokeWidth = 2f,
            )

            //точки
            drawCircle(
                color = Color.Red,
                radius = 5f,
                center = Offset(x, y),
            )
        }

        for (i in 0 until pointsCount - 1) {
            //линии между точками
            drawLine(
                start = listOffset[i],
                end = listOffset[i + 1],
                color = Color.Red,
                strokeWidth = 3f
            )
        }
        val path = Path().apply {
            moveTo(listOffset[0].x, listOffset[0].y)
            for (i in 1 until pointsCount) {
                lineTo(listOffset[i].x, listOffset[i].y)
            }
            lineTo(graphXEnd, graphYEnd)
            lineTo(graphXStart, graphYEnd)
            close()
        }

        drawPath(
            path = path,
            brush = Brush.verticalGradient(mutableListOf(Color.Red.copy(alpha = 0.3f), Color.Transparent, )),
        )
    }
}

@Preview
@Composable
private fun GraphItemPreview() {
    GraphItem(
        pointsCount = 4,
        pointsValues = listOf(10, 0, 40, 50)
    )
}