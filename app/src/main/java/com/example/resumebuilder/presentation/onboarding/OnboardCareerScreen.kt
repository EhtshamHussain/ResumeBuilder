package com.example.resumebuilder.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import com.example.resumebuilder.R
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.screens.CustomButton
import com.example.resumebuilder.presentation.onboarding.components.FeatureCard
import com.example.resumebuilder.presentation.onboarding.components.FloatingIllustration
import com.example.resumebuilder.presentation.onboarding.components.OnboardingDescription
import com.example.resumebuilder.presentation.onboarding.components.OnboardingTitle

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardCareerScreen(
    onGetStartedClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        FloatingIllustration(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Image(painter = painterResource(R.drawable.resumescreenlogo), contentDescription = null)
        }

        Spacer(modifier = Modifier.height(40.dp))

        OnboardingTitle(
            text = "Ready to Land Your Dream Job?",
            highlightPart = "Job?"
        )

        Spacer(modifier = Modifier.height(16.dp))

        OnboardingDescription(
            text = "Download and share PDF resumes perfectly optimized for modern hiring platforms. Your next career move starts here."
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureCard(
                icon = Icons.Default.Verified,
                title = "ATS-Ready",
                description = "Optimized for HR.",
                modifier = Modifier.weight(1f)
            )
            FeatureCard(
                icon = Icons.Default.RocketLaunch,
                title = "Quick Share",
                description = "Send in one tap.",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        CustomButton(
            text = "Get Started",
            isWithIcon = true,
            containerColor = Color(0xFF005EA4),
            contentColor = Color.White,
            onClick = onGetStartedClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Back to previous",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.clickable { onBackClick() }
        )
    }
}
