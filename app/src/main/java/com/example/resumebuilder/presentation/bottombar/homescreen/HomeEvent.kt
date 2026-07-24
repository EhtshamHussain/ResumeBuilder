package com.example.resumebuilder.presentation.bottombar.homescreen

sealed class HomeEvent {
    data object Profile : HomeEvent()
    data object AddResumeClicked : HomeEvent()
    data class ResumeClicked(val resumeId: Long) : HomeEvent()
    data class DeleteIconClicked(val resumeId: Long) : HomeEvent()
    data class EditResumeClicked(val resumeId: Long) : HomeEvent()
    data object DeleteConfirmed : HomeEvent()
    data object DeleteCancelled : HomeEvent()
}