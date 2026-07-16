package com.example.resumebuilder.presentation.onboarding.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FloatingIllustration(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit={}
) {

    /*** it creates parent animation state that helps to perform multiple animation at the same time
    the declarative pattern of handling layout animations, where a parent composable observes a state change and orchestrates smooth transitions */
    val infiniteTransition = rememberInfiniteTransition(label = "floating")

    /** infiniteTransition.animateFloat used for generic custom type of animation e:g Custom Objects, Rectangles, ya Integers
        infiniteTransition.animateValue() */

    /** color changing animation with infinite duration
        infiniteTransition.animateColor() */

 /**   Positions (X, Y coordinates), Opacity (Alpha/Fade), Rotation, or  Scale (Size zooming) */

    val translationY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -20f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "yAnimation"
    )
    Box(
        modifier = modifier.graphicsLayer(translationY = translationY),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
@Composable
fun FeatureCard(
    icon: ImageVector,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF4F7FA))
            .padding(12.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF005EA4),
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 10.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun OnboardingTitle(
    text: String,
    highlightPart: String,
    modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        val parts = text.split(highlightPart)
        if (parts.isNotEmpty()) {
            append(parts[0])
            withStyle(style = SpanStyle(color = Color(0xFF005EA4), fontWeight = FontWeight.Bold)) {
                append(highlightPart)
            }
            if (parts.size > 1) {
                append(parts[1])
            }
        } else {
            append(text)
        }
    }

    Text(
        text = annotatedString,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier.padding(horizontal = 24.dp),
        lineHeight = 32.sp
    )
}

@Composable
fun OnboardingDescription(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.Gray,
        textAlign = TextAlign.Center,
        modifier = modifier.padding(horizontal = 40.dp),
        lineHeight = 20.sp
    )
}
