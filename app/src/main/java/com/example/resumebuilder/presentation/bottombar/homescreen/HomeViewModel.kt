package com.example.resumebuilder.presentation.bottombar.homescreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.resumebuilder.data.local.preference.SessionManager
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import com.example.resumebuilder.domain.repository.ResumeRepository
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.extension.vmScopeMain
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel

class HomeViewModel(
    private val resumeRepository: ResumeRepository,
    private val resumeDraftRepository: ResumeDraftRepository,
    private val sessionManager: SessionManager
) : BaseViewModel() {
    var state by mutableStateOf(HomeState())
        private set
    val email = sessionManager.getUserEmail() ?: ""
    private var hasStartedObserving = false

    init {
        observeResumes()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Profile -> {
                navigate(NavigationAction.NavigateTo(Routes.Profile))
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

            is HomeEvent.EditResumeClicked -> {
                EditResume(resumeId = event.resumeId)
            }

            is HomeEvent.ResumeClicked -> {
                navigate(NavigationAction.NavigateTo(Routes.ResumePreview(resumeId = event.resumeId)))
            }
        }
    }

    fun EditResume(resumeId: Long) {
        vmScopeMain {
            // Existing resume ka data Room se fetch karo
            resumeRepository.getResumeById(resumeId)
                .onSuccess { savedResume ->
                    resumeDraftRepository.updateDraft {
                        savedResume.draft.copy(editingResumeId = resumeId)
                    }
                    navigate(NavigationAction.NavigateTo(Routes.CreateResume))
                }
        }
    }

    fun observeResumes() = vmScopeMain {
        if (hasStartedObserving) return@vmScopeMain
        hasStartedObserving = true
//        val resumeResult=resumeRepository.getResumeByEmail(email)
//        val savedResume = resumeResult.getOrNull() ?: run {
//            showError("Failed to load resume data")
//            return@vmScopeMain
//        }
//        state=state.copy(
//            isLoading = false,
//            resumes = listOf(savedResume)
//        )
        val resumes = resumeRepository.getAllResumes()
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

