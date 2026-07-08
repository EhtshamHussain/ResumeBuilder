package com.example.resumebuilder.presentation.resumebuilder.skillsprojects

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.domain.model.Certification
import com.example.resumebuilder.domain.model.Project
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.UUID

class SkillsProjectsViewModel(
    private val resumeDraftRepository: ResumeDraftRepository
) : BaseViewModel() {

    var state by mutableStateOf(SkillsProjectsState())
        private set

    fun onEvent(event: SkillsProjectsEvent) {
        when (event) {
            is SkillsProjectsEvent.ScreenEntered -> loadExistingDraft()

            // ---- Skills ----
            is SkillsProjectsEvent.SkillInputChanged -> {
                state = state.copy(currentSkillInput = event.value)
            }

            is SkillsProjectsEvent.AddSkillClicked -> {
                addSkill()
            }

            is SkillsProjectsEvent.RemoveSkill -> {
                state = state.copy(skills = state.skills.filter { it != event.skill })
            }

            // ---- Projects ----
            is SkillsProjectsEvent.AddProjectClicked -> {
                val newProject = Project(id = UUID.randomUUID().toString())
                state = state.copy(projects = state.projects + newProject)
            }

            is SkillsProjectsEvent.RemoveProject -> {
                state = state.copy(projects = state.projects.filter { it.id != event.id })
            }

            is SkillsProjectsEvent.ProjectTitleChanged -> {
                updateProject(event.id) { it.copy(title = event.value) }
            }

            is SkillsProjectsEvent.ProjectLinkChanged -> {
                updateProject(event.id) { it.copy(link = event.value) }
            }

            is SkillsProjectsEvent.ProjectDescriptionChanged -> {
                updateProject(event.id) { it.copy(description = event.value) }
            }

            // ---- Certifications ----
            is SkillsProjectsEvent.AddCertificationClicked -> {
                val newCert = Certification(id = UUID.randomUUID().toString())
                state = state.copy(certifications = state.certifications + newCert)
            }

            is SkillsProjectsEvent.RemoveCertification -> {
                state = state.copy(certifications = state.certifications.filter { it.id != event.id })
            }

            is SkillsProjectsEvent.CertificationNameChanged -> {
                updateCertification(event.id) { it.copy(name = event.value) }
            }

            is SkillsProjectsEvent.CertificationIssuerChanged -> {
                updateCertification(event.id) { it.copy(issuer = event.value) }
            }

            is SkillsProjectsEvent.CertificationDateChanged -> {
                updateCertification(event.id) { it.copy(issuedDate = event.value) }
            }

            is SkillsProjectsEvent.NextStepClicked -> {
                validateAndProceed()
            }
        }
    }

    // Skill add karne se pehle check: khali nahi honi chahiye, aur duplicate nahi honi chahiye
    private fun addSkill() {
        val skill = state.currentSkillInput.trim()
        if (skill.isBlank()) return

        if (state.skills.contains(skill)) {
            showError("This skill is already added")
            return
        }

        state = state.copy(
            skills = state.skills + skill,
            currentSkillInput = ""   // input field clear kar do add hone ke baad
        )
    }

    private fun updateProject(id: String, update: (Project) -> Project) {
        state = state.copy(
            projects = state.projects.map { if (it.id == id) update(it) else it }
        )
    }

    private fun updateCertification(id: String, update: (Certification) -> Certification) {
        state = state.copy(
            certifications = state.certifications.map { if (it.id == id) update(it) else it }
        )
    }

    private fun loadExistingDraft() {
        val draft = resumeDraftRepository.draft.value
        state = state.copy(
            skills = draft.skills,
            projects = draft.projects,
            certifications = draft.certifications
        )
    }

    private fun saveToRepository() {
        resumeDraftRepository.updateDraft { currentDraft ->
            currentDraft.copy(
                skills = state.skills,
                projects = state.projects,
                certifications = state.certifications
            )
        }
    }

    private fun validateAndProceed() {
        if (state.skills.isEmpty()) {
            showError("Please add at least one skill")
            return
        }

        viewModelScope.launch {
            saveToRepository()
            navigate(NavigationAction.NavigateTo(Routes.PolishResume))
        }
    }
}