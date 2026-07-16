package com.example.resumebuilder.presentation.bottombar.homescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.resumebuilder.domain.repository.ResumeRepository
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.extension.vmScopeMain
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel

class HomeViewModel(
    private val resumeRepository: ResumeRepository
) : BaseViewModel() {
    var state by mutableStateOf(HomeState())
        private set
    private var hasStartedObserving = false

    init {
        observeResumes()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SettingsClicked -> {
                navigate(NavigationAction.NavigateTo(Routes.Settings))
            }

            is HomeEvent.AddResumeClicked -> {
                navigate(NavigationAction.NavigateTo(Routes.CreateResume))
            }

            is HomeEvent.DeleteIconClicked -> {
                state = state.copy(resumeIdPendingDelete = event.resumeId)
            }

            is HomeEvent.DeleteCancelled -> {
                state = state.copy(resumeIdPendingDelete = null)
            }

            is HomeEvent.DeleteConfirmed -> {
                deleteResume()
            }

            is HomeEvent.ResumeClicked -> {
                navigate(NavigationAction.NavigateTo(Routes.ResumePreview(resumeId = event.resumeId)))
            }
        }
    }
    fun observeResumes() = vmScopeMain {
        if (hasStartedObserving) return@vmScopeMain
        hasStartedObserving = true
        resumeRepository.getAllResumes()
            .collect { resumes ->
                state = state.copy(
                    isLoading = false,
                    resumes = resumes
                )
            }
    }

    private fun deleteResume() {
        val resumeId = state.resumeIdPendingDelete ?: return

        vmScopeMain {
            resumeRepository.deleteResume(resumeId)
                .onSuccess {
                    state = state.copy(resumeIdPendingDelete = null)
                    showToast("Resume deleted")
                }
                .onFailure { error ->
                    state = state.copy(
                        resumeIdPendingDelete = null,
                        error = error.message ?: "Failed to delete resume"
                    )
                }
        }
    }
}
