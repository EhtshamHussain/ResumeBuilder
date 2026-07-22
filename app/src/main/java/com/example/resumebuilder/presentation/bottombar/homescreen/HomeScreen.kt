package com.example.resumebuilder.presentation.bottombar.homescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Person3
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.R
import com.example.resumebuilder.domain.model.SavedResume
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.presentation.shared.presentation.component.circularbar.CircularProgress
import com.example.resumebuilder.presentation.shared.presentation.component.topappbar.AppScaffold
import kotlinx.coroutines.flow.SharedFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState = HomeState(),
    baseUiEvent: SharedFlow<BaseViewModel.BaseViewModelEvents>,
    actionEvent: (HomeEvent) -> Unit = {},
    navigation: (NavigationAction) -> Unit = {}
) {
    BaseScreen(
        baseUIEvents = baseUiEvent,
        navigation = navigation,
    ) {
        AppScaffold(
            title = "Resume Builder",
            actionIcon = Icons.Default.Person3,
            onActionClick = {
                actionEvent(HomeEvent.SettingsClicked)
            },
//            floatingActionButton = {
//                FloatingActionButton(
//                    onClick = {
//                        actionEvent(HomeEvent.AddResumeClicked)
//                    },
//                    containerColor = Color(0xFF005EA4)
//                ) {
//                    Icon(
//                        Icons.Default.Add,
//                        contentDescription = null
//                    )
//                }
//            }
        ) { paddingValues ->
            when {
                state.isLoading -> {
                    CircularProgress(modifier = Modifier.padding(paddingValues))
                }

                state.resumes.isEmpty() -> {
                    EmptyResumesContent(modifier = Modifier.padding(paddingValues))
                }

                else -> {
                    ResumesListContent(
                        resumes = state.resumes,
                        onResumeClick = { resumeId -> actionEvent(HomeEvent.ResumeClicked(resumeId)) },
                        onDeleteClick = { resumeId ->
                            actionEvent(
                                HomeEvent.DeleteIconClicked(
                                    resumeId
                                )
                            )
                        },
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }

    if (state.resumeIdPendingDelete != null) {
        DeleteConfirmationDialog(
            onConfirm = { actionEvent(HomeEvent.DeleteConfirmed) },
            onCancel = { actionEvent(HomeEvent.DeleteCancelled) }
        )
    }
}
@Composable
private fun DeleteConfirmationDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(text = "Delete Resume?") },
        text = { Text(text = "Are you sure you want to delete this resume? This action cannot be undone.") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Delete", color = Color(0xFFD32F2F))
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text(text = "Cancel", color = Color.Gray)
            }
        }
    )
}
@Composable
private fun ResumesListContent(
    resumes: List<SavedResume>,
    onResumeClick: (Long) -> Unit,
    onDeleteClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(resumes) { resume ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onResumeClick(resume.id) },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(
                            text = resume.resumeName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = resume.draft.professionalTitle,
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }


                    IconButton(onClick = { onDeleteClick(resume.id) }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete resume",
                            tint = Color(0xFFD32F2F)
                        )
                    }
                }
            }
        }
    }
}
@Composable
private fun EmptyResumesContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.3f))

        Image(
            painter = painterResource(id = R.drawable.resumeimg),
            contentDescription = "No Resumes",
            modifier = Modifier
                .size(240.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "No resumes yet",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Tap the + icon to start building your professional Resume.",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp),
            lineHeight = 22.sp
        )
        Spacer(modifier = Modifier.weight(0.5f))

    }
}