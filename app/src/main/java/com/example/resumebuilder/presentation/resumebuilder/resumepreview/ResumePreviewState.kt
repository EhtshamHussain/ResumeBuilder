package com.example.resumebuilder.presentation.resumebuilder.resumepreview

data class ResumePreviewState(
    val resumeId: Long = 0L,
    val renderedHtml: String = "",
    val resumeName: String = "",
    val isLoading: Boolean = true,
    val error: String? = null
)