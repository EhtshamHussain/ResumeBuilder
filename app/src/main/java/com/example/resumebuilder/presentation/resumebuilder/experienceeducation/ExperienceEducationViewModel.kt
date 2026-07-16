package com.example.resumebuilder.presentation.resumebuilder.experienceeducation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import androidx.room.util.TableInfo
import com.example.resumebuilder.domain.model.Education
import com.example.resumebuilder.domain.model.WorkExperience
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.extension.vmScopeMain
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.screens.CustomButton
import kotlinx.coroutines.launch
import java.util.UUID

class ExperienceEducationViewModel(
    private val resumeDraftRepository: ResumeDraftRepository
) : BaseViewModel() {
    var state by mutableStateOf(ExperienceEducationState())
        private set

    init {
        loadExistingDraft()
    }

    fun onEvent(event: ExperienceEducationEvent) {
        when (event) {
            is ExperienceEducationEvent.AddWorkExperienceClicked -> {
                val newEntry = WorkExperience(id = UUID.randomUUID().toString())
                state = state.copy(workExperiences = state.workExperiences + newEntry)
            }

            is ExperienceEducationEvent.RemoveWorkExperience -> {
                state = state.copy(
                    workExperiences = state.workExperiences.filter { it.id != event.id }
                )
            }

            is ExperienceEducationEvent.CompanyChanged -> {
                updateWorkExperience(event.id) { it.copy(company = event.value) }
            }

            is ExperienceEducationEvent.RoleChanged -> {
                updateWorkExperience(event.id) { it.copy(role = event.value) }
            }

            is ExperienceEducationEvent.WorkStartDateChanged -> {
                updateWorkExperience(event.id) { it.copy(startDate = event.value) }
            }

            is ExperienceEducationEvent.WorkEndDateChanged -> {
                updateWorkExperience(event.id) { it.copy(endDate = event.value) }
            }

            is ExperienceEducationEvent.ResponsibilitiesChanged -> {
                updateWorkExperience(event.id) { it.copy(responsibilities = event.value) }
            }

            is ExperienceEducationEvent.AddEducationClicked -> {
                val newEntry = Education(id = UUID.randomUUID().toString())
                state = state.copy(educations = state.educations + newEntry)
            }

            is ExperienceEducationEvent.RemoveEducation -> {
                state = state.copy(
                    educations = state.educations.filter { it.id != event.id }
                )
            }

            is ExperienceEducationEvent.InstitutionChanged -> {
                updateEducation(event.id) { it.copy(institution = event.value) }
            }

            is ExperienceEducationEvent.DegreeChanged -> {
                updateEducation(event.id) { it.copy(degree = event.value) }
            }

            is ExperienceEducationEvent.EduStartDateChanged -> {
                updateEducation(event.id) { it.copy(startDate = event.value) }
            }

            is ExperienceEducationEvent.GraduationDateChanged -> {
                updateEducation(event.id) { it.copy(graduationDate = event.value) }
            }

            is ExperienceEducationEvent.SaveDraftClicked -> {
                saveToRepository()
                showToast("Draft saved")
            }

            is ExperienceEducationEvent.NextStepClicked -> {
                validateAndProceed()
            }
        }
    }

    private fun updateWorkExperience(id: String, update: (WorkExperience) -> WorkExperience) {
        state = state.copy(
            workExperiences = state.workExperiences.map { entry ->
                if (entry.id == id) update(entry) else entry
            }
        )
    }

    private fun updateEducation(id: String, update: (Education) -> Education) {
        state = state.copy(
            educations = state.educations.map { entry ->
                if (entry.id == id) update(entry) else entry
            }
        )
    }

    private fun loadExistingDraft() {
        val draft = resumeDraftRepository.draft.value
        state = state.copy(
            workExperiences = draft.workExperiences,
            educations = draft.educations
        )
    }

    private fun saveToRepository() {
        resumeDraftRepository.updateDraft { currentDraft ->
            currentDraft.copy(
                workExperiences = state.workExperiences,
                educations = state.educations
            )
        }
    }

    private fun validateAndProceed() {
        if (state.workExperiences.isEmpty()) {
            showError("Please add at least one work experience")
            return
        }

        vmScopeMain {
            saveToRepository()
            navigate(NavigationAction.NavigateTo(Routes.SkillsProjects))
        }
    }
}
