package com.example.resumebuilder.presentation.resumebuilder.resumepreview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.domain.model.ResumeDraft
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import com.example.resumebuilder.domain.repository.ResumeRepository
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.extension.vmScopeMain
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class ResumePreviewViewModel(
    private val resumeId: Long,
    private val resumeRepository: ResumeRepository,
    private val resumeDraftRepository: ResumeDraftRepository
) : BaseViewModel() {
    var state by mutableStateOf(ResumePreviewState(resumeId = resumeId))
        private set

    init {
        loadAndRenderResume()
    }

    fun onEvent(event: ResumePreviewEvent) {
        when (event) {
            is ResumePreviewEvent.ChangeTemplateClicked -> {
                loadDraftAndGoToTemplateSelect()
            }
        }
    }

    private fun loadAndRenderResume() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            val resumeResult = resumeRepository.getResumeById(resumeId)
            val htmlResult = resumeRepository.renderResumeHtml(resumeId)
            if (resumeResult.isSuccess && htmlResult.isSuccess) {
                state = state.copy(
                    isLoading = false,
                    resumeName = resumeResult.getOrNull()?.resumeName ?: "",
                    renderedHtml = htmlResult.getOrNull() ?: ""
                )
            } else {
                state = state.copy(
                    isLoading = false,
                    error = "Failed to load resume"
                )
            }
        }
    }

    private fun loadDraftAndGoToTemplateSelect() {
        vmScopeMain {
            val resumeResult = resumeRepository.getResumeById(resumeId)
            val savedResume = resumeResult.getOrNull() ?: run {
                showError("Failed to load resume data")
                return@vmScopeMain
            }
            resumeDraftRepository.updateDraft { savedResume.draft }
            navigate(
                NavigationAction.NavigateTo(
                    route = Routes.TemplateSelect(existingResumeId = resumeId)
                )
            )
        }
    }
}


