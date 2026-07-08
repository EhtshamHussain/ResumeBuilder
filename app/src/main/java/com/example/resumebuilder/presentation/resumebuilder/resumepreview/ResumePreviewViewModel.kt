package com.example.resumebuilder.presentation.resumebuilder.resumepreview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.domain.model.ResumeDraft
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import com.example.resumebuilder.domain.repository.ResumeRepository
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

// resumeId navigation argument se ata hai (Routes.ResumePreview(resumeId))
class ResumePreviewViewModel(
    private val resumeId: Long,
    private val resumeRepository: ResumeRepository,
    private  val resumeDraftRepository: ResumeDraftRepository
) : BaseViewModel() {

    var state by mutableStateOf(ResumePreviewState(resumeId = resumeId))
        private set

    fun onEvent(event: ResumePreviewEvent) {
        when (event) {
            is ResumePreviewEvent.ScreenEntered -> loadAndRenderResume()

            is ResumePreviewEvent.ChangeTemplateClicked -> {
                loadDraftAndGoToTemplateSelect()

            }

            is ResumePreviewEvent.DownloadPdfClicked -> {

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

    // "Change Template" click hone pa: is resume ka saved data wapas Draft mein daal do,
    // taake Template Select screen ke paas khali data na ho, aur wo "update mode" mein jaye
    private fun loadDraftAndGoToTemplateSelect() {
        viewModelScope.launch {
            val resumeResult = resumeRepository.getResumeById(resumeId)
            val savedResume = resumeResult.getOrNull() ?: run {
                showError("Failed to load resume data")
                return@launch
            }

            // Draft repository ko is resume ke poore data se bhar do
            resumeDraftRepository.updateDraft { savedResume.draft }

            // Template Select screen ko batao ke ye EXISTING resume update ho raha hai (naya nahi)
            navigate(
                NavigationAction.NavigateTo(
                    route = Routes.TemplateSelect(existingResumeId = resumeId)
                )
            )
        }
    }
}