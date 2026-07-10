package com.example.resumebuilder.presentation.resumebuilder.resumepreview

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.screens.CustomButton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Preview(showBackground = true , showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumePreviewScreen(
    state: ResumePreviewState = ResumePreviewState(),
    baseUiEvent: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow(),
    navigation: (NavigationAction) -> Unit = {},
    actionEvent: (ResumePreviewEvent) -> Unit = {},
) {
    val context = LocalContext.current


    val webView = remember { WebView(context) }

    LaunchedEffect(Unit) {
        actionEvent(ResumePreviewEvent.ScreenEntered)
    }

    LaunchedEffect(state.renderedHtml) {
        if (state.renderedHtml.isNotBlank()) {
            webView.loadDataWithBaseURL(null, state.renderedHtml, "text/html", "UTF-8", null)
        }
    }

    BaseScreen(
        baseUIEvents = baseUiEvent,
        navigation = navigation
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = state.resumeName.ifBlank { "Resume Preview" },
                            fontSize = 16.sp,
                            color = Color(0xFF005EA4)
                        )
                    },
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
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    if (state.isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Color(0xFF005EA4))
                        }
                    } else {
                        AndroidView(
                            factory = {
                                webView.apply {
                                    webViewClient = WebViewClient()
                                    settings.javaScriptEnabled = false
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                state.error?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomButton(
                        onClick = { actionEvent(ResumePreviewEvent.ChangeTemplateClicked) },
                        text = "Change Template",
                        modifier = Modifier.weight(1f),
                        contentColor = Color(0xFF005EA4)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    CustomButton(
                        onClick = {
                            actionEvent(ResumePreviewEvent.DownloadPdfClicked)
                            // System print/save dialog yahan trigger hota hai —
                            // isi WebView object se jo already HTML load kar chuka hai
                            printWebViewAsPdf(
                                context = context,
                                webView = webView,
                                jobName = state.resumeName.ifBlank { "Resume" }
                            )
                        },
                        text = "Download",
                        isLogin = true,
                        modifier = Modifier.weight(1f),
                        contentColor = Color.White,
                        containerColor = Color(0xFF005EA4)
                    )
                }
            }
        }
    }
}