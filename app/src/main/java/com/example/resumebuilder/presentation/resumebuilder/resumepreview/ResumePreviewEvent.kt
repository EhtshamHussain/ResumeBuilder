package com.example.resumebuilder.presentation.resumebuilder.resumepreview

sealed class ResumePreviewEvent {
    data object ChangeTemplateClicked : ResumePreviewEvent()
}