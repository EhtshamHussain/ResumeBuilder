package com.example.resumebuilder.presentation.templateselect

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.domain.model.ResumeTemplate
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.presentation.shared.presentation.component.circularbar.CircularProgress
import com.example.resumebuilder.presentation.shared.presentation.component.topappbar.AppScaffold
import com.example.resumebuilder.presentation.shared.presentation.component.buttons.CustomButton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateSelectScreen(
    state: TemplateSelectState = TemplateSelectState(),
    baseUiEvent: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow(),
    navigation: (NavigationAction) -> Unit = {},
    actionEvent: (TemplateSelectEvent) -> Unit = {},
    isFromBottomBar: Boolean = false
) {
    BaseScreen(
        baseUIEvents = baseUiEvent,
        navigation = navigation
    ) {
        AppScaffold(
            title = "Choose Template",
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onNavigationClick = { navigation(NavigationAction.PopBackStack) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Build your path to success.", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Select a template that matches your industry and career goals. Our AI will handle the formatting.",
                    fontSize = 13.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (state.isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgress()
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Saving your resume...", fontSize = 14.sp, color = Color.Gray)
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(state.templates) { template ->
                            TemplateCard(
                                template = template,
                                onSelectClick = {
                                    if(isFromBottomBar){
                                        actionEvent(TemplateSelectEvent.TemplateSelectedOnBb(template))

                                    }else {
                                        actionEvent(TemplateSelectEvent.TemplateSelected(template))
                                    }
                                }
                            )
                        }
                    }
                    state.error?.let { error ->
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = error, color = Color.Red, fontSize = 14.sp)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
@Composable
private fun TemplateCard(
    template: ResumeTemplate,
    onSelectClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F0F7)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Image(
                    painter = painterResource(template.img), contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = template.displayName, fontSize = 15.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = template.description,
                fontSize = 11.sp,
                color = Color.Gray,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomButton(
                onClick = onSelectClick,
                text = "Select",
                modifier = Modifier.fillMaxWidth(),
                contentColor = Color.White,
                containerColor = Color(0xFF005EA4)
            )
        }
    }
}