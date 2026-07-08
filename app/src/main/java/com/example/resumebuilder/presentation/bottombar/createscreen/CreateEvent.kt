package com.example.resumebuilder.presentation.bottombar.createscreen

sealed class CreateEvent {
    data class ResumeTitleChanged(val title: String) : CreateEvent()
    data class JobDescriptionChanged(val description: String) : CreateEvent()
    data object GenerateClicked : CreateEvent()
}