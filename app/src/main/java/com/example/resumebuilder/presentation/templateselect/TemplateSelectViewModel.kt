package com.example.resumebuilder.presentation.templateselect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.domain.model.ResumeTemplate
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import com.example.resumebuilder.domain.repository.ResumeRepository
import com.example.resumebuilder.presentation.bottombar.screens.BottomBarScreens
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class TemplateSelectViewModel(
    private val existingResumeId: Long?,
    private val resumeDraftRepository: ResumeDraftRepository,
    private val resumeRepository: ResumeRepository   // ye agle step mein banayenge — final Room save ke liye
) : BaseViewModel() {

    var state by mutableStateOf(TemplateSelectState())
        private set

    fun onEvent(event: TemplateSelectEvent) {
        when (event) {
            is TemplateSelectEvent.TemplateSelected -> {
                selectTemplateAndSave(event.template)
            }
        }
    }
    private fun selectTemplateAndSave(template: ResumeTemplate) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)

            resumeDraftRepository.updateDraft { it.copy(selectedTemplateId = template.id) }
            val finalDraft = resumeDraftRepository.draft.value

            // Ye hi asal fix hai: agar existingResumeId hai, UPDATE karo (naya row nahi banega),
            // warna naya SAVE karo (pehli dafa resume create ho raha hai)
            val result = if (existingResumeId != null) {
                resumeRepository.updateResume(existingResumeId, finalDraft)
                    .map { existingResumeId }   // Result<Unit> ko Result<Long> mein convert kiya, taake id aage use ho sake
            } else {
                resumeRepository.saveResume(finalDraft)
            }

            result
                .onSuccess { savedResumeId ->
                    state = state.copy(isLoading = false, selectedTemplate = template)
                    resumeDraftRepository.clearDraft()

                    // popUpTo se poori wizard + purana Preview screen bhi stack se hata do —
                    // taake back press pa seedha Home pa jaye, wizard dobara na chale
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