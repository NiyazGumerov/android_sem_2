package ru.itis.homework11

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
        }

        val circularView6 = CircularSegmentedView(this).apply {
            layoutParams = LayoutParams(600, 600)
            setSegmentCount(6)
            setColors(
                mutableListOf(
                    Color.RED, Color.CYAN,
                    Color.YELLOW, Color.BLUE, Color.MAGENTA, Color.GRAY
                )
            )
        }
        val circularView5 = CircularSegmentedView(this).apply {
            layoutParams = LayoutParams(600, 600)
            setSegmentCount(5)
            setColors(
                mutableListOf(
                    Color.RED,
                    Color.YELLOW, Color.BLUE, Color.MAGENTA, Color.RED
                )
            )
        }
        val circularView4 = CircularSegmentedView(this).apply {
            layoutParams = LayoutParams(600, 600)
            setSegmentCount(4)
            setColors(
                mutableListOf(
                    Color.RED, Color.CYAN,
                    Color.MAGENTA, Color.GRAY
                )
            )
        }
        val circularView3 = CircularSegmentedView(this).apply {
            layoutParams = LayoutParams(600, 600)
            setSegmentCount(3)
            setColors(mutableListOf(Color.BLUE, Color.MAGENTA, Color.GRAY))
        }

        layout.addView(circularView6)
        layout.addView(circularView5)
        layout.addView(circularView4)
        layout.addView(circularView3)
        setContentView(layout)
    }
}