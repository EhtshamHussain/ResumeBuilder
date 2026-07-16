package com.example.resumebuilder.presentation.bottombar.homescreen

import com.example.resumebuilder.domain.model.SavedResume

data class HomeState(
    val isFloatBtnClicked: Boolean = false,
    val isLoading: Boolean = true,
    val resumes: List<SavedResume> = emptyList(),
    val error: String? = null,
    val resumeIdPendingDelete: Long? = null
)
