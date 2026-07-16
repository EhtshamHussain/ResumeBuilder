package com.example.resumebuilder.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import com.example.resumebuilder.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardWelcomeScreen(
    onNextClick: () -> Unit = {},
    onSkipClick: () -> Unit = {},
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
                .height(250.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F0F7)),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .size(220.dp, 180.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .padding(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Image(
                        painter = painterResource(R.drawable.welocmefloatingcard),
                        contentDescription = null
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        OnboardingTitle(
            text = "Generate resumes using AI.",
            highlightPart = "using AI."
        )

        Spacer(modifier = Modifier.height(16.dp))

        OnboardingDescription(
            text = "Turn your career history into a high-impact document in seconds. Smart keyword matching ensures you bypass automated filters."
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureCard(
                icon = Icons.Default.AutoAwesome,
                title = "Instant Drafts",
                description = "Done in bits.",
                modifier = Modifier.weight(1f)
            )
            FeatureCard(
                icon = Icons.Default.Work,
                title = "Role Targeted",
                description = "Matches job specs.",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Skip",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.clickable { onSkipClick() }
            )

            CustomButton(
                text = "Next",
                isWithIcon = true,
                containerColor = Color(0xFF005EA4),
                contentColor = Color.White,
                onClick = onNextClick,
                modifier = Modifier.width(140.dp)
            )
        }
    }
}
