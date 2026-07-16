package com.example.resumebuilder.presentation.bottombar.createscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CreateViewModel(
) : BaseViewModel() {
    var state by mutableStateOf(CreateState())
        private set

    fun onEvent(event: CreateEvent) {
        when (event) {
            is CreateEvent.ResumeTitleChanged -> {
                state = state.copy(resumeTitle = event.title)
            }

            is CreateEvent.JobDescriptionChanged -> {
                state = state.copy(jobDescription = event.description)
            }

            is CreateEvent.GenerateClicked -> {
                generateResume()
            }
        }
    }

    private fun generateResume() {
        if (state.resumeTitle.isBlank()) {
            showError("Please enter a resume title")
            return
        }

        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            state = state.copy(isLoading = false)
        }
    }
}