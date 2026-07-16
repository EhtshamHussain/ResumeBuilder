package com.example.resumebuilder.presentation.resumebuilder.polishresume

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.presentation.shared.presentation.component.labels.LabelText
import com.example.resumebuilder.presentation.shared.presentation.component.linearprogressindicator.LinearProgressBar
import com.example.resumebuilder.presentation.shared.presentation.component.topappbar.AppScaffold
import com.example.resumebuilder.screens.CustomButton
import com.example.resumebuilder.screens.CustomTextField
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PolishResumeScreen(
    state: PolishResumeState = PolishResumeState(),
    baseUiEvent: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow(),
    navigation: (NavigationAction) -> Unit = {},
    actionEvent: (PolishResumeEvent) -> Unit = {},
) {
    BaseScreen(
        baseUIEvents = baseUiEvent,
        navigation = navigation
    ) {
        AppScaffold(
            title = "Polish Your Resume",
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onNavigationClick = { navigation(NavigationAction.PopBackStack) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "STEP 4 OF 4", fontSize = 12.sp, color = Color(0xFF005EA4))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Polish Your Resume", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Add the final touches to make your profile stand out to recruiters and AI filters.",
                    fontSize = 13.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressBar(progress = 1f)


                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Languages", fontSize = 16.sp, color = Color(0xFF005EA4))
                Spacer(modifier = Modifier.height(10.dp))
                ChipInputRow(
                    value = state.currentLanguageInput,
                    prefix = "e.g. English (Native)",
                    onValueChange = { actionEvent(PolishResumeEvent.LanguageInputChanged(it)) },
                    onAddClick = { actionEvent(PolishResumeEvent.AddLanguageClicked) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state.languages.forEach { language ->
                        RemovableChip(text = language) {
                            actionEvent(PolishResumeEvent.RemoveLanguage(language))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Interests", fontSize = 16.sp, color = Color(0xFF005EA4))
                Spacer(modifier = Modifier.height(10.dp))
                ChipInputRow(
                    value = state.currentInterestInput,
                    prefix = "e.g. Machine Learning",
                    onValueChange = { actionEvent(PolishResumeEvent.InterestInputChanged(it)) },
                    onAddClick = { actionEvent(PolishResumeEvent.AddInterestClicked) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state.interests.forEach { interest ->
                        RemovableChip(text = interest) {
                            actionEvent(PolishResumeEvent.RemoveInterest(interest))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Achievements", fontSize = 16.sp, color = Color(0xFF005EA4))
                Spacer(modifier = Modifier.height(10.dp))
                ChipInputRow(
                    value = state.currentAchievementInput,
                    prefix = "E.g., Winner of 2023 Google AI Hackathon...",
                    onValueChange = { actionEvent(PolishResumeEvent.AchievementInputChanged(it)) },
                    onAddClick = { actionEvent(PolishResumeEvent.AddAchievementClicked) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state.achievements.forEach { achievement ->
                        RemovableChip(text = achievement) {
                            actionEvent(PolishResumeEvent.RemoveAchievement(achievement))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Professional Links", fontSize = 16.sp, color = Color(0xFF005EA4))
                Spacer(modifier = Modifier.height(12.dp))

                LabelText(text = "LinkedIn")
                CustomTextField(
                    value = state.linkedInUrl,
                    onValueChange = { actionEvent(PolishResumeEvent.LinkedInChanged(it)) },
                    placeholder = "linkedin.com/in/alex-smith",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))
                LabelText(text = "GitHub")
                CustomTextField(
                    value = state.githubUrl,
                    onValueChange = { actionEvent(PolishResumeEvent.GithubChanged(it)) },
                    placeholder = "github.com/username",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))
                LabelText(text = "Portfolio / Personal Website")
                CustomTextField(
                    value = state.portfolioUrl,
                    onValueChange = { actionEvent(PolishResumeEvent.PortfolioChanged(it)) },
                    placeholder = "https://yourportfolio.com",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Include References", fontSize = 14.sp)
                        Text(
                            text = "We'll add 'Available upon request' or specific names.",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                    }
                    Switch(
                        checked = state.includeReferences,
                        onCheckedChange = {
                            actionEvent(
                                PolishResumeEvent.IncludeReferencesToggled(
                                    it
                                )
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                state.error?.let { error ->
                    Text(text = error, color = Color.Red, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomButton(
                        onClick = { actionEvent(PolishResumeEvent.SaveDraftClicked) },
                        text = "Save Draft",
                        contentColor = Color(0xFF005EA4)
                    )
                    Spacer(modifier = Modifier.width(6.dp))

                    if (state.isLoading) {
                        CircularProgressIndicator(color = Color(0xFF005EA4))
                    } else {
                        CustomButton(
                            onClick = { actionEvent(PolishResumeEvent.FinishClicked) },
                            text = "Finish ",
                            isLogin = true,
                            contentColor = Color.White,
                            containerColor = Color(0xFF005EA4)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
@Composable
private fun ChipInputRow(
    value: String,
    prefix: String,
    onValueChange: (String) -> Unit,
    onAddClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = prefix,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = onAddClick) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add",
                tint = Color(0xFF005EA4)
            )
        }
    }
}
@Composable
private fun RemovableChip(text: String, onRemove: () -> Unit) {
    SuggestionChip(
        onClick = onRemove,
        label = { Text(text = text) },
        icon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Remove $text",
                modifier = Modifier.height(16.dp)
            )
        },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = Color(0xFFE8F0F7),
            labelColor = Color(0xFF005EA4)
        )
    )
}