package com.example.resumebuilder.presentation.resumebuilder.skillsprojects

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.domain.model.Certification
import com.example.resumebuilder.domain.model.Project
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.presentation.shared.presentation.component.labels.LabelText
import com.example.resumebuilder.presentation.shared.presentation.component.linearprogressindicator.LinearProgressBar
import com.example.resumebuilder.presentation.shared.presentation.component.topappbar.AppScaffold
import com.example.resumebuilder.presentation.shared.presentation.component.buttons.CustomButton
import com.example.resumebuilder.presentation.shared.presentation.component.textfield.CustomTextField
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SkillsProjectsScreen(
    state: SkillsProjectsState = SkillsProjectsState(),
    baseUiEvent: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow(),
    navigation: (NavigationAction) -> Unit = {},
    actionEvent: (SkillsProjectsEvent) -> Unit = {},
) {
    BaseScreen(
        baseUIEvents = baseUiEvent,
        navigation = navigation
    ) {
        AppScaffold(
            title = "Skills & Projects",
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
                Text(text = "STEP 3 OF 4", fontSize = 12.sp, color = Color(0xFF005EA4))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Skills & Projects", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Highlight your technical expertise and showcase the real-world impact of your work through projects and certifications.",
                    fontSize = 13.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressBar(0.75f)


                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Skills", fontSize = 16.sp, color = Color(0xFF005EA4))
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomTextField(
                        value = state.currentSkillInput,
                        onValueChange = { actionEvent(SkillsProjectsEvent.SkillInputChanged(it)) },
                        placeholder = "Add a skill (e.g. Python, UI Design)...",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = { actionEvent(SkillsProjectsEvent.AddSkillClicked) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add Skill",
                            tint = Color(0xFF005EA4)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state.skills.forEach { skill ->
                        SuggestionChip(
                            onClick = { actionEvent(SkillsProjectsEvent.RemoveSkill(skill)) },
                            label = { Text(text = skill) },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Remove $skill",
                                    modifier = Modifier.height(16.dp)
                                )
                            },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = Color(0xFFE8F0F7),
                                labelColor = Color(0xFF005EA4)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Projects", fontSize = 16.sp, color = Color(0xFF005EA4))
                    CustomButton(
                        onClick = { actionEvent(SkillsProjectsEvent.AddProjectClicked) },
                        text = "Add Project",
                        icon = Icons.Filled.Add,
                        isWithIcon = true,
                        contentColor = Color(0xFF005EA4)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                state.projects.forEach { project ->
                    ProjectCard(project = project, actionEvent = actionEvent)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Certifications", fontSize = 16.sp, color = Color(0xFF005EA4))
                Spacer(modifier = Modifier.height(12.dp))

                state.certifications.forEach { cert ->
                    CertificationCard(certification = cert, actionEvent = actionEvent)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                CustomButton(
                    onClick = { actionEvent(SkillsProjectsEvent.AddCertificationClicked) },
                    text = "Add New Certification",
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

                if (state.isLoading) {
                    CircularProgressIndicator(color = Color(0xFF005EA4))
                } else {
                    CustomButton(
                        onClick = { actionEvent(SkillsProjectsEvent.NextStepClicked) },
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
@Composable
private fun ProjectCard(
    project: Project,
    actionEvent: (SkillsProjectsEvent) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LabelText(text = "PROJECT TITLE")
                IconButton(onClick = {
                    actionEvent(SkillsProjectsEvent.RemoveProject(project.id))
                }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Remove", tint = Color.Red)
                }
            }

            CustomTextField(
                value = project.title,
                onValueChange = {
                    actionEvent(SkillsProjectsEvent.ProjectTitleChanged(project.id, it))
                },
                placeholder = "e.g. Autonomous Delivery Bot",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))
            LabelText(text = "LINK (OPTIONAL)")
            CustomTextField(
                value = project.link,
                onValueChange = {
                    actionEvent(SkillsProjectsEvent.ProjectLinkChanged(project.id, it))
                },
                placeholder = "https://github.com/...",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))
            LabelText(text = "DESCRIPTION")
            CustomTextField(
                value = project.description,
                onValueChange = {
                    actionEvent(SkillsProjectsEvent.ProjectDescriptionChanged(project.id, it))
                },
                placeholder = "Describe the project and its impact...",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
@Composable
private fun CertificationCard(
    certification: Certification,
    actionEvent: (SkillsProjectsEvent) -> Unit
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
                LabelText(text = "CERTIFICATION NAME")
                IconButton(onClick = {
                    actionEvent(SkillsProjectsEvent.RemoveCertification(certification.id))
                }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Remove", tint = Color.Red)
                }
            }

            CustomTextField(
                value = certification.name,
                onValueChange = {
                    actionEvent(SkillsProjectsEvent.CertificationNameChanged(certification.id, it))
                },
                placeholder = "e.g. AWS Certified Solutions Architect",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))
            LabelText(text = "ISSUER")
            CustomTextField(
                value = certification.issuer,
                onValueChange = {
                    actionEvent(
                        SkillsProjectsEvent.CertificationIssuerChanged(
                            certification.id,
                            it
                        )
                    )
                },
                placeholder = "e.g. Amazon Web Services",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))
            LabelText(text = "ISSUED DATE")
            CustomTextField(
                value = certification.issuedDate,
                onValueChange = {
                    actionEvent(SkillsProjectsEvent.CertificationDateChanged(certification.id, it))
                },
                placeholder = "e.g. Jan 2023",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}