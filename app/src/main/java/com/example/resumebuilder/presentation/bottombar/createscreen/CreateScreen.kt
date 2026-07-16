package com.example.resumebuilder.presentation.bottombar.createscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.presentation.shared.presentation.component.labels.LabelText
import com.example.resumebuilder.screens.CustomButton
import com.example.resumebuilder.screens.CustomTextField
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun CreateScreen(
    state: CreateState = CreateState(),
    baseUiEvent: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow(),
    navigation: (NavigationAction) -> Unit = {},
    actionEvent: (CreateEvent) -> Unit = {},
) {
    BaseScreen(
        baseUIEvents = baseUiEvent,
        navigation = navigation
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Create Resume", fontSize = 26.sp)
            Spacer(modifier = Modifier.height(24.dp))

            LabelText(
                text = "Resume Title",
                modifier = Modifier.fillMaxWidth()
            )
            CustomTextField(
                value = state.resumeTitle,
                onValueChange = { actionEvent(CreateEvent.ResumeTitleChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            LabelText(
                text = "Job Description",
                modifier = Modifier.fillMaxWidth()
            )
            CustomTextField(
                value = state.jobDescription,
                onValueChange = { actionEvent(CreateEvent.JobDescriptionChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )

            state.error?.let { error ->
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = error, color = Color.Red, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (state.isLoading) {
                CircularProgressIndicator(color = Color(0xFF005EA4))
            } else {
                CustomButton(
                    onClick = { actionEvent(CreateEvent.GenerateClicked) },
                    text = "Generate with AI",
                    modifier = Modifier.fillMaxWidth(),
                    contentColor = Color.White,
                    containerColor = Color(0xFF005EA4)
                )
            }
        }
    }
}