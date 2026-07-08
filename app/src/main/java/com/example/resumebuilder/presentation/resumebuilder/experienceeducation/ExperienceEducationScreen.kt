package com.example.resumebuilder.presentation.resumebuilder.experienceeducation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.domain.model.Education
import com.example.resumebuilder.domain.model.WorkExperience
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.presentation.shared.presentation.component.labels.LabelText
import com.example.resumebuilder.screens.CustomButton
import com.example.resumebuilder.screens.CustomTextField
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceEducationScreen(
    state: ExperienceEducationState = ExperienceEducationState(),
    baseUiEvent: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow(),
    navigation: (NavigationAction) -> Unit = {},
    actionEvent: (ExperienceEducationEvent) -> Unit = {},
) {
    LaunchedEffect(Unit) {
        actionEvent(ExperienceEducationEvent.ScreenEntered)
    }

    BaseScreen(
        baseUIEvents = baseUiEvent,
        navigation = navigation
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("CareerSync AI", color = Color(0xFF005EA4), fontSize = 18.sp) },
                    navigationIcon = {
                        IconButton(onClick = { navigation(NavigationAction.PopBackStack) }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
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
                Text(text = "STEP 2 OF 4", fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Build your professional foundation", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Add your work history and academic achievements. Our AI will help you optimize the descriptions later.",
                    fontSize = 13.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { 0.5f },
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF005EA4)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Work Experience", fontSize = 16.sp, color = Color(0xFF005EA4))
                Spacer(modifier = Modifier.height(12.dp))

                // Har WorkExperience entry ke liye ek card render karo
                state.workExperiences.forEach { experience ->
                    WorkExperienceCard(
                        experience = experience,
                        actionEvent = actionEvent
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                CustomButton(
                    onClick = { actionEvent(ExperienceEducationEvent.AddWorkExperienceClicked) },
                    text = "Add Work Experience",
                    icon = Icons.Filled.Add,
                    isWithIcon = true,
                    modifier = Modifier.fillMaxWidth(),
                    contentColor = Color(0xFF005EA4)
                )

                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Education", fontSize = 16.sp, color = Color(0xFF005EA4))
                Spacer(modifier = Modifier.height(12.dp))

                state.educations.forEach { education ->
                    EducationCard(
                        education = education,
                        actionEvent = actionEvent
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                CustomButton(
                    onClick = { actionEvent(ExperienceEducationEvent.AddEducationClicked) },
                    text = "Add Education",
                    icon = Icons.Filled.Add,
                    isWithIcon = true,
                    modifier = Modifier.fillMaxWidth(),
                    contentColor = Color(0xFF005EA4)
                )

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
                        onClick = { actionEvent(ExperienceEducationEvent.SaveDraftClicked) },
                        text = "Save Draft",
                        modifier = Modifier.weight(1f),
                        contentColor = Color(0xFF005EA4)
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                    if (state.isLoading) {
                        CircularProgressIndicator(color = Color(0xFF005EA4))
                    } else {
                        CustomButton(
                            onClick = { actionEvent(ExperienceEducationEvent.NextStepClicked) },
                            text = "Next Step",
                            isLogin = true,
                            modifier = Modifier.weight(1f),
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

// Alag composable banaya — taake ExperienceEducationScreen readable rahe
// aur ye card independently reusable/testable ho
@Composable
private fun WorkExperienceCard(
    experience: WorkExperience,
    actionEvent: (ExperienceEducationEvent) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "COMPANY", fontSize = 12.sp, color = Color.Gray)
                IconButton(onClick = {
                    actionEvent(ExperienceEducationEvent.RemoveWorkExperience(experience.id))
                }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Remove", tint = Color.Red)
                }
            }

            CustomTextField(
                value = experience.company,
                onValueChange = {
                    actionEvent(ExperienceEducationEvent.CompanyChanged(experience.id, it))
                },
                placeholder = "e.g. Google",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))
            LabelText(text = "ROLE")
            CustomTextField(
                value = experience.role,
                onValueChange = {
                    actionEvent(ExperienceEducationEvent.RoleChanged(experience.id, it))
                },
                placeholder = "e.g. Senior UX Designer",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    LabelText(text = "START DATE")
                    CustomTextField(
                        value = experience.startDate,
                        onValueChange = {
                            actionEvent(ExperienceEducationEvent.WorkStartDateChanged(experience.id, it))
                        },
                        placeholder = "MM/YYYY",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    LabelText(text = "END DATE")
                    CustomTextField(
                        value = experience.endDate,
                        onValueChange = {
                            actionEvent(ExperienceEducationEvent.WorkEndDateChanged(experience.id, it))
                        },
                        placeholder = "MM/YYYY",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            LabelText(text = "KEY RESPONSIBILITIES")
            CustomTextField(
                value = experience.responsibilities,
                onValueChange = {
                    actionEvent(ExperienceEducationEvent.ResponsibilitiesChanged(experience.id, it))
                },
                placeholder = "Describe your achievements...",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun EducationCard(
    education: Education,
    actionEvent: (ExperienceEducationEvent) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "INSTITUTION", fontSize = 12.sp, color = Color.Gray)
                IconButton(onClick = {
                    actionEvent(ExperienceEducationEvent.RemoveEducation(education.id))
                }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Remove", tint = Color.Red)
                }
            }

            CustomTextField(
                value = education.institution,
                onValueChange = {
                    actionEvent(ExperienceEducationEvent.InstitutionChanged(education.id, it))
                },
                placeholder = "e.g. Stanford University",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))
            LabelText(text = "DEGREE")
            CustomTextField(
                value = education.degree,
                onValueChange = {
                    actionEvent(ExperienceEducationEvent.DegreeChanged(education.id, it))
                },
                placeholder = "e.g. Master of Computer Science",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    LabelText(text = "START DATE")
                    CustomTextField(
                        value = education.startDate,
                        onValueChange = {
                            actionEvent(ExperienceEducationEvent.EduStartDateChanged(education.id, it))
                        },
                        placeholder = "MM/YYYY",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    LabelText(text = "GRADUATION DATE")
                    CustomTextField(
                        value = education.graduationDate,
                        onValueChange = {
                            actionEvent(ExperienceEducationEvent.GraduationDateChanged(education.id, it))
                        },
                        placeholder = "MM/YYYY",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}