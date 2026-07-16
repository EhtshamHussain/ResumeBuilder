package com.example.resumebuilder.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.R
import com.example.resumebuilder.screens.CustomButton
import com.example.resumebuilder.presentation.onboarding.components.FloatingIllustration
import com.example.resumebuilder.presentation.onboarding.components.OnboardingDescription
import com.example.resumebuilder.presentation.onboarding.components.OnboardingTitle

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardResumeScreen(
    onNextClick: () -> Unit = {},
    onSkipClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 20.dp, bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "CareerSync AI",
                color = Color(0xFF005EA4),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "Skip",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onSkipClick() }
            )
        }

        Spacer(modifier = Modifier.weight(0.5f))
        FloatingIllustration(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        ) {
            Image(painter = painterResource(R.drawable.finalscreenlogo), contentDescription = null)
        }

        Spacer(modifier = Modifier.height(40.dp))

        OnboardingTitle(
            text = "Create professional resumes.",
            highlightPart = "resumes."
        )

        Spacer(modifier = Modifier.height(16.dp))

        OnboardingDescription(
            text = "Leverage advanced AI to build a stand-out profile that catches the eyes of top-tier recruiters in seconds."
        )

        Spacer(modifier = Modifier.weight(1f))

        CustomButton(
            text = "Next",
            isWithIcon = true,
            containerColor = Color(0xFF005EA4),
            contentColor = Color.White,
            onClick = onNextClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(56.dp)
        )
    }
}
