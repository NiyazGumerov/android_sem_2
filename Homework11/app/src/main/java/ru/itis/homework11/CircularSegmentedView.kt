package ru.itis.homework11

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt

class CircularSegmentedView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private var segmentCount = 0
    private val segmentColors = mutableListOf<Int>()

    private var colorError = false

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val selectedPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textAlign = Paint.Align.CENTER
        textSize = 64f
        typeface = Typeface.DEFAULT_BOLD
    }

    private var activeSegment = -1
    private val rectFBig = RectF()
    private val rectFSmall = RectF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!colorError) {
            val radius = min(width, height) * 0.45f
            val cx = width / 2f
            val cy = height / 2f
            rectFBig.set(cx - radius, cy - radius, cx + radius, cy + radius)
            rectFSmall.set(cx - radius / 3, cy - radius / 3, cx + radius / 3, cy + radius / 3)

            val sweepAngle = 360f / segmentCount
            var startAnglee = 180f

            for (i in 0 until segmentCount) {
                val theta = 2 * Math.PI.toFloat() / segmentCount
                val startAngle = i * theta + Math.PI.toFloat()
                val endAngle = (i + 1) * theta + Math.PI.toFloat()

                val x1 = cx + radius * cos(startAngle).toFloat()
                val y1 = cy + radius * sin(startAngle).toFloat()
                val x2 = cx + radius * cos(endAngle).toFloat()
                val y2 = cy + radius * sin(endAngle).toFloat()

                val x3 = cx + radius / 3 * cos(startAngle).toFloat()
                val y3 = cy + radius / 3 * sin(startAngle).toFloat()
                val x4 = cx + radius / 3 * cos(endAngle).toFloat()
                val y4 = cy + radius / 3 * sin(endAngle).toFloat()

                val xCenterFirst = (x1 + x3) / 2
                val yCenterFirst = (y1 + y3) / 2
                val rectFFirstForEach = RectF()
                rectFFirstForEach.set(
                    xCenterFirst - radius / 3,
                    yCenterFirst - radius / 3,
                    xCenterFirst + radius / 3,
                    yCenterFirst + radius / 3
                )

                val xCenterSecond = (x2 + x4) / 2
                val yCenterSecond = (y2 + y4) / 2
                val rectFSecondForEach = RectF()
                rectFSecondForEach.set(
                    xCenterSecond - radius / 3,
                    yCenterSecond - radius / 3,
                    xCenterSecond + radius / 3,
                    yCenterSecond + radius / 3
                )

                val path = Path()
                path.arcTo(rectFBig, startAnglee, sweepAngle)
                path.arcTo(rectFSecondForEach, startAnglee + sweepAngle, 180f)
                path.arcTo(rectFSmall, startAnglee + sweepAngle, -sweepAngle)
                path.arcTo(rectFFirstForEach, startAnglee + 180, -180f)
                path.close()

                paint.color = segmentColors[i % segmentColors.size]
                selectedPaint.color = lightenColor(paint.color)

                canvas.drawPath(
                    path, if (i == activeSegment) selectedPaint else paint
                )

                startAnglee += sweepAngle

            }

            canvas.drawText(
                "$segmentCount", cx, cy - (textPaint.descent() + textPaint.ascent()) / 2, textPaint
            )
        } else {
            paint.color = Color.RED
            paint.textSize = 48f
            canvas.drawText(
                context.getString(R.string.color_error), 0f, height / 2f, paint
            )
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = event.x
                val y = event.y

                val radius = min(width, height) * 0.45f
                val cx = width / 2f
                val cy = height / 2f
                val distance = sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy))

                if (distance > radius || distance < radius / 3) {
                    activeSegment = -1
                    invalidate()
                    return false
                }

                var angle = atan2(cy - y, x - cx).toFloat()

                var angleDeg = Math.toDegrees(angle.toDouble()).toFloat()
                if (angleDeg < 0) angleDeg += 360f

                angleDeg = (angleDeg + 180f) % 360f

                val sweepAngle = 360f / segmentCount
                val segment = (angleDeg / sweepAngle).toInt()

                val correctedSegment = segment.coerceIn(0, segmentCount - 1)

                activeSegment = segmentCount - 1 - correctedSegment // Инвертируем индекс сегмента
                invalidate()

                return true
            }
        }
        return super.onTouchEvent(event)
    }


    private fun lightenColor(color: Int): Int {
        val factor = 0.3f

        val a = Color.alpha(color)
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)

        val newR = (r + (255 - r) * factor).toInt()
        val newG = (g + (255 - g) * factor).toInt()
        val newB = (b + (255 - b) * factor).toInt()

        return Color.argb(a, newR, newG, newB)
    }

    fun setSegmentCount(count: Int) {
        segmentCount = count
        invalidate()
    }

    fun setColors(colors: List<Int>) {
        colorError = when {
            colors.isEmpty() -> true
            colors.hasConsecutiveDuplicates() -> true
            colors.first() == colors.last() -> true
            else -> false
        }

        if (!colorError) {
            segmentColors.clear()
            segmentColors.addAll(colors)
        }
        invalidate()
    }

    private fun List<Int>.hasConsecutiveDuplicates(): Boolean {
        return (0 until size - 1).any { this[it] == this[it + 1] }
    }
}
