package com.example.resumebuilder.presentation.resumebuilder.resumepreview

data class ResumePreviewState(
    val resumeId: Long = 0L,
    val renderedHtml: String = "",       // WebView isko load karega
    val resumeName: String = "",
    val isLoading: Boolean = true,
//    val isDownloading: Boolean = false,
    val error: String? = null
)