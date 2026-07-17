package com.example.resumebuilder.presentation.bottombar.profilescreen

import android.content.Context
import android.print.PrintAttributes
import android.print.PrintManager
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.presentation.shared.presentation.component.circularbar.CircularProgress
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun ProfileScreen(
    state: ProfileState = ProfileState(),
    baseUiEvent: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow(),
    navigation: (NavigationAction) -> Unit = {},
    actionEvent: (ProfileEvent) -> Unit = {},
) {
    BaseScreen(
        baseUIEvents = baseUiEvent,
        navigation = navigation
    ) {
        if (state.isLoading) {
            CircularProgress()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F0F7)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile picture placeholder",
                    tint = Color(0xFF005EA4),
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = state.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = state.email,
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFFE8F0F7), shape = RoundedCornerShape(20.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Text(text = "Premium Member", fontSize = 12.sp, color = Color(0xFF005EA4))
            }

            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF005EA4)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "Activity Overview",
                            fontSize = 13.sp,
                            color = Color(0xFFCFE1F2)
                        )
                        Icon(
                            imageVector = Icons.Filled.Description,
                            contentDescription = null,
                            tint = Color(0xFFCFE1F2),
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = state.totalResumesCreated.toString().padStart(2, '0'),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Total resumes created",
                        fontSize = 12.sp,
                        color = Color(0xFFCFE1F2)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    value = state.jobMatches.toString(),
                    label = "JOB MATCHES",
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    value = "${state.matchRatePercent}%",
                    label = "MATCH RATE",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "Account Settings",
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column {
                    SettingsRow(
                        icon = Icons.Filled.Person,
                        label = "Personal Information",
                        onClick = { actionEvent(ProfileEvent.PersonalInformationClicked) }
                    )
                    SettingsRow(
                        icon = Icons.Filled.Notifications,
                        label = "Notification Preferences",
                        onClick = { actionEvent(ProfileEvent.NotificationPreferencesClicked) }
                    )
                    SettingsRow(
                        icon = Icons.Filled.Lock,
                        label = "Privacy & Security",
                        onClick = { actionEvent(ProfileEvent.PrivacySecurityClicked) },
                        showDivider = false
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            state.error?.let { error ->
                Text(text = error, color = Color.Red, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(12.dp))
            }
            OutlinedButton(
                onClick = { actionEvent(ProfileEvent.LogoutClicked) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFD32F2F)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF3D4D4)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Logout", fontSize = 15.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
@Composable
private fun StatCard(value: String, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = value, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label, fontSize = 11.sp, color = Color.Gray)
        }
    }
}
@Composable
private fun SettingsRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit,
    showDivider: Boolean = true
) {
    Column(
        modifier = Modifier.clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F0F7)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFF005EA4),
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
        if (showDivider) {
            androidx.compose.material3.HorizontalDivider(
                color = Color(0xFFEDEDED),
                modifier = Modifier.padding(start = 66.dp)
            )
        }
    }
}