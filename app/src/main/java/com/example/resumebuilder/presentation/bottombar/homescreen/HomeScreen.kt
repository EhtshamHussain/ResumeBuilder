package com.example.resumebuilder.presentation.bottombar.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
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
            actionIcon = Icons.Default.Person,
            onActionClick = {
                actionEvent(HomeEvent.Profile)
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF8FAFC)) // Soft background for contrast
            ) {
                when {
                    state.isLoading -> {
                        CircularProgress()
                    }

                    state.resumes.isEmpty() -> {
                        EmptyResumesContent()
                    }

                    else -> {
                        ResumesListContent(
                            resumes = state.resumes,
                            onResumeClick = { resumeId ->
                                actionEvent(
                                    HomeEvent.ResumeClicked(
                                        resumeId
                                    )
                                )
                            },
                            onDeleteClick = { resumeId ->
                                actionEvent(
                                    HomeEvent.DeleteIconClicked(
                                        resumeId
                                    )
                                )
                            },
                            onEditClick = { resumeId ->
                                actionEvent(HomeEvent.EditResumeClicked(resumeId))
                            }
                        )
                    }
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
    onEditClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
    ) {
        items(resumes) { resume ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onResumeClick(resume.id) },
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .size(100.dp)
                        .background(color = Color.Gray)
                ) {
                    VerticalDivider()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Leading Icon Box
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color(0xFFE8F0F7), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null,
                            tint = Color(0xFF005EA4),
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = resume.resumeName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF1E293B)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = resume.draft.professionalTitle.ifEmpty { "Professional Resume" },
                            fontSize = 13.sp,
                            color = Color(0xFF64748B),
                            fontWeight = FontWeight.Medium
                        )
                    }
                    // Action Buttons
                    Row {
                        IconButton(
                            onClick = { onEditClick(resume.id) },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "Edit",
                                tint = Color(0xFF005EA4),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        IconButton(
                            onClick = { onDeleteClick(resume.id) },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete",
                                tint = Color(0xFFEF4444),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
//
//@Composable
//private fun ResumesListContent(
//    resumes: List<SavedResume>,
//    onResumeClick: (Long) -> Unit,
//    onDeleteClick: (Long) -> Unit,
//    onEditClick: (Long) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    LazyColumn(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp)
//    ) {
//        items(resumes) { resume ->
//            // Modern Outer Card
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable { onResumeClick(resume.id) },
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.White
//                ),
//                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
//                shape = RoundedCornerShape(20.dp)
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    // Top Accent Banner (Premium Look ke liye)
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(6.dp)
//                            .background(
//                                brush = Brush.horizontalGradient(
//                                    colors = listOf(Color(0xFF2563EB), Color(0xFF38BDF8))
//                                )
//                            )
//                    )
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp)
//                    ) {
//                        // Header Section: Icon + Title + Delete Button
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            // Gradient Icon Container
//                            Box(
//                                modifier = Modifier
//                                    .size(48.dp)
//                                    .background(
//                                        color = Color(0xFFEFF6FF),
//                                        shape = RoundedCornerShape(14.dp)
//                                    ),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Description,
//                                    contentDescription = null,
//                                    tint = Color(0xFF2563EB),
//                                    modifier = Modifier.size(24.dp)
//                                )
//                            }
//
//                            Spacer(modifier = Modifier.width(12.dp))
//
//                            // Resume Title & Subtitle Badge
//                            Column(modifier = Modifier.weight(1f)) {
//                                Text(
//                                    text = resume.resumeName,
//                                    fontSize = 17.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = Color(0xFF0F172A)
//                                )
//                                Spacer(modifier = Modifier.height(4.dp))
//
//                                // Tag/Badge for Professional Title
//                                Box(
//                                    modifier = Modifier
//                                        .background(
//                                            color = Color(0xFFF1F5F9),
//                                            shape = RoundedCornerShape(6.dp)
//                                        )
//                                        .padding(horizontal = 8.dp, vertical = 3.dp)
//                                ) {
//                                    Text(
//                                        text = resume.draft.professionalTitle.ifEmpty { "Professional Resume" },
//                                        fontSize = 11.sp,
//                                        color = Color(0xFF475569),
//                                        fontWeight = FontWeight.SemiBold
//                                    )
//                                }
//                            }
//
//                            // Subtle Delete Button (Top Right corner par clean lagta hai)
//                            IconButton(
//                                onClick = { onDeleteClick(resume.id) },
//                                modifier = Modifier
//                                    .size(36.dp)
//                                    .background(
//                                        color = Color(0xFFFEF2F2),
//                                        shape = CircleShape
//                                    )
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Filled.Delete,
//                                    contentDescription = "Delete",
//                                    tint = Color(0xFFEF4444),
//                                    modifier = Modifier.size(18.dp)
//                                )
//                            }
//                        }
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        HorizontalDivider(
//                            color = Color(0xFFF1F5F9),
//                            thickness = 1.dp
//
//                        )
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        // Bottom View Action Button (Full Width Stylish Button)
//                        Surface(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .clip(RoundedCornerShape(12.dp))
//                                .clickable { onEditClick(resume.id) },
//                            color = Color(0xFF2563EB)
//                        ) {
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(vertical = 10.dp),
//                                horizontalArrangement = Arrangement.Center,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Visibility,
//                                    contentDescription = null,
//                                    tint = Color.White,
//                                    modifier = Modifier.size(18.dp)
//                                )
//                                Spacer(modifier = Modifier.width(8.dp))
//                                Text(
//                                    text = "View CV",
//                                    fontSize = 14.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = Color.White
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
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