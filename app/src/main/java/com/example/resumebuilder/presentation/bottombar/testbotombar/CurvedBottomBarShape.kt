package com.example.resumebuilder.presentation.bottombar.testbotombar

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CurvedBottomBarShape(
    private val curveRadius: Float = 90f,
    private val cornerRadius: Float = 60f
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

            // Start from top left corner, applying a rounded edge
            moveTo(0f, cornerRadius)
            quadraticTo(0f, 0f, cornerRadius, 0f)

            // Line to the start of the central curve
            // Adjust the curve entry distance based on radius size
            val curveStartX = centerX - curveRadius * 1.4f
            lineTo(curveStartX, 0f)

            // Smooth cubic bezier curves to form the center bulge
            cubicTo(
                centerX - curveRadius * 0.9f, 0f,
                centerX - curveRadius * 0.8f, -curveRadius * 0.5f,
                centerX - curveRadius, -curveRadius * 0.5f
            )
            cubicTo(
                centerX - curveRadius * 0.3f, -curveRadius * 1.1f,
                centerX + curveRadius * 0.3f, -curveRadius * 1.1f,
                centerX + curveRadius, -curveRadius * 0.5f
            )
            cubicTo(
                centerX + curveRadius * 0.8f, -curveRadius * 0.5f,
                centerX + curveRadius * 0.9f, 0f,
                centerX + curveRadius * 1.4f, 0f
            )

            // Line to top right corner
            lineTo(width - cornerRadius, 0f)
            quadraticTo(width, 0f, width, cornerRadius)

            // Bottom edges
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }
        return Outline.Generic(path)
    }
}
