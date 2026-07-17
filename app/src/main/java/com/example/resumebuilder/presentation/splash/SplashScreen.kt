package com.example.resumebuilder.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreen(
    onNavigate: () -> Unit = {}
) {
    val alpha = remember { Animatable(0f) }
    val scale = remember { Animatable(0.8f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(1f, animationSpec = tween(1500))
        scale.animateTo(1.5f, animationSpec = tween(1500))
        scale.animateTo(1f, animationSpec = tween(1500))
        alpha.animateTo(0f, animationSpec = tween(1500))
        delay(1000.milliseconds)
        onNavigate()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .alpha(alpha.value)
                .scale(scale.value)
        ) {
            Card(
                modifier = Modifier.size(160.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFf8f2e6)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "App Logo",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "AI Resume Builder",
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "PRECISION ENGINEERED CAREERS",
                fontSize = 12.sp,
                color = Color.Black.copy(alpha = 0.8f),
                fontWeight = FontWeight.Light,
                letterSpacing = 2.sp
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(180.dp)
                .padding(bottom = 26.dp),
            thickness = 3.dp,
            color = Color.Black.copy(alpha = 0.4f)
        )
    }
}
