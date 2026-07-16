package com.example.resumebuilder.presentation.resumebuilder.contactsummary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.presentation.shared.presentation.component.circularbar.CircularProgress
import com.example.resumebuilder.presentation.shared.presentation.component.labels.LabelText
import com.example.resumebuilder.presentation.shared.presentation.component.linearprogressindicator.LinearProgressBar
import com.example.resumebuilder.presentation.shared.presentation.component.topappbar.AppScaffold
import com.example.resumebuilder.screens.CustomButton
import com.example.resumebuilder.screens.CustomTextField
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactSummaryScreen(
    state: ContactSummaryState = ContactSummaryState(),
    baseUiEvent: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow(),
    navigation: (NavigationAction) -> Unit = {},
    actionEvent: (ContactSummaryEvent) -> Unit = {},
) {
    BaseScreen(
        baseUIEvents = baseUiEvent,
        navigation = navigation
    ) {
        AppScaffold(
            title = "Contact & Summary",
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onNavigationClick = {
                navigation(NavigationAction.PopBackStack)
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "STEP 1 OF 4", fontSize = 12.sp, color = Color(0xFF005EA4))
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Contact & Summary", fontSize = 22.sp)
                    Text(text = "25%", color = Color(0xFF005EA4), fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressBar(0.25f)

                Spacer(modifier = Modifier.height(20.dp))

                LabelText(text = "Full Name")
                Spacer(modifier = Modifier.height(6.dp))
                CustomTextField(
                    value = state.fullName,
                    onValueChange = {
                        actionEvent(ContactSummaryEvent.FullNameChanged(it))
                    },
                    placeholder = "e.g. Cpl Price",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                LabelText(text = "Professional Title")
                Spacer(modifier = Modifier.height(6.dp))
                CustomTextField(
                    value = state.professionalTitle,
                    onValueChange = { actionEvent(ContactSummaryEvent.ProfessionalTitleChanged(it)) },
                    placeholder = "e.g. Senior Command officer",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                LabelText(text = "Email Address")
                Spacer(modifier = Modifier.height(6.dp))
                CustomTextField(
                    value = state.email,
                    onValueChange = { actionEvent(ContactSummaryEvent.EmailChanged(it)) },
                    placeholder = "alex.price@example.com",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                LabelText(text = "Phone Number")
                Spacer(modifier = Modifier.height(6.dp))
                CustomTextField(
                    value = state.phoneNumber,
                    onValueChange = { actionEvent(ContactSummaryEvent.PhoneNumberChanged(it)) },
                    placeholder = "+1 (555) 000-0000",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                LabelText(text = "Current Location")
                Spacer(modifier = Modifier.height(6.dp))
                CustomTextField(
                    value = state.currentLocation,
                    onValueChange = { actionEvent(ContactSummaryEvent.CurrentLocationChanged(it)) },
                    placeholder = "City, Country",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                LabelText(text = "Professional Summary")
                Spacer(modifier = Modifier.height(6.dp))
                CustomTextField(
                    value = state.professionalSummary,
                    onValueChange = { actionEvent(ContactSummaryEvent.ProfessionalSummaryChanged(it)) },
                    placeholder = "Briefly describe your career goals and key achievements...",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Aim for 3-5 sentences that highlight your most impactful work.",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(24.dp))

                state.error?.let { error ->
                    Text(text = error, color = Color.Red, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                if (state.isLoading) {
                    CircularProgress()
                } else {
                    CustomButton(
                        onClick = { actionEvent(ContactSummaryEvent.NextStepClicked) },
                        text = "Next Step",
                        isLogin = true,
                        modifier = Modifier.fillMaxWidth(),
                        contentColor = Color.White,
                        containerColor = Color(0xFF005EA4)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
