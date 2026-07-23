package com.example.resumebuilder.presentation.templateselect

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.resumebuilder.domain.model.ResumeTemplate
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import com.example.resumebuilder.domain.repository.ResumeRepository
import com.example.resumebuilder.presentation.bottombar.routes.BottomBarScreens
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.extension.vmScopeMain
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel

class TemplateSelectViewModel(
    private val existingResumeId: Long?,
    private val resumeDraftRepository: ResumeDraftRepository,
    private val resumeRepository: ResumeRepository
) : BaseViewModel() {

    var state by mutableStateOf(TemplateSelectState())
        private set

    fun onEvent(event: TemplateSelectEvent) {
        when (event) {
            is TemplateSelectEvent.TemplateSelected -> {
                selectTemplateAndSave(event.template)
            }
            is TemplateSelectEvent.TemplateSelectedOnBb -> {
                selectTemplateAndStartGettingData(event.template)
            }
        }
    }
    private fun selectTemplateAndStartGettingData(template: ResumeTemplate) {
        vmScopeMain {
            resumeDraftRepository.updateDraft { it.copy(selectedTemplateId = template.id) }
            Log.d("TAG", "selectTemplateAndStartGettingData: ${template.id}")

            navigate(NavigationAction.NavigateTo(Routes.CreateResume))
            Log.d("TAG", "selectTemplateAndStartGettingData: ${resumeDraftRepository.draft.value.selectedTemplateId}")
        }
    }
    private fun selectTemplateAndSave(template: ResumeTemplate) {
       vmScopeMain {
            state = state.copy(isLoading = true, error = null)

            resumeDraftRepository.updateDraft { it.copy(selectedTemplateId = template.id) }
            val finalDraft = resumeDraftRepository.draft.value


            val result = if (existingResumeId != null) {
               resumeRepository.updateResume(existingResumeId, finalDraft).map { existingResumeId }
            } else {
                resumeRepository.saveResume(finalDraft)
            }

            result
                .onSuccess { savedResumeId ->
                    state = state.copy(isLoading = false, selectedTemplate = template)
                    resumeDraftRepository.clearDraft()
                    navigate(
                        NavigationAction.NavigateToClearingUpTo(
                            route = Routes.ResumePreview(resumeId = savedResumeId),
                            upToRoute = BottomBarScreens.Home,
                            inclusive = false
                        )
                    )
                }
                .onFailure { error ->
                    state = state.copy(isLoading = false, error = error.message ?: "Failed to save resume")
                }
        }
    }
}
