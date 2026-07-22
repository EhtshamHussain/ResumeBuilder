package com.example.resumebuilder.presentation.bottombar.testbotombar

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class DynamicNotchBottomBarShape(
    private val cornerRadius: Float = 20f,
    private val notchRadius: Float = 105f,
    private val controlPointDistance: Float = 40f
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val width = size.width
            val height = size.height
            val centerX = width / 2


            moveTo(0f, cornerRadius)
            quadraticTo(0f, 0f, cornerRadius, 0f)

            val notchStartX = centerX - notchRadius - controlPointDistance
            lineTo(notchStartX, 0f)

            cubicTo(
                centerX - notchRadius, 0f,
                centerX - notchRadius, notchRadius * 0.95f,
                centerX, notchRadius * 0.95f
            )

            cubicTo(
                centerX + notchRadius, notchRadius * 0.95f,
                centerX + notchRadius, 0f,
                centerX + notchRadius + controlPointDistance, 0f
            )

            lineTo(width - cornerRadius, 0f)
            quadraticTo(width, 0f, width, cornerRadius)

            lineTo(width, height)
            lineTo(0f, height)
            close()
        }
        return Outline.Generic(path)
    }
}
