package com.example.resumebuilder.domain.model



data class SavedResume(
    val id: Long,
    val resumeName: String,
    val draft: ResumeDraft,
    val pdfFilePath: String?,
    val createdAt: Long
)